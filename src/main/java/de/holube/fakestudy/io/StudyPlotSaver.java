package de.holube.fakestudy.io;

import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.study.category.Category;
import de.holube.fakestudy.study.category.CorrelationCategory;
import de.holube.fakestudy.study.category.NumCategory;
import de.holube.fakestudy.study.category.SelectionCategory;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchart.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class StudyPlotSaver {

    private static final int plotWidth = 400;
    private static final int plotHeight = 300;

    private final Study study;
    private final String exportPath;
    private final int studyNumber;

    private final List<CategoryChart> categoryCharts = new ArrayList<>();
    private final List<XYChart> xyCharts = new ArrayList<>();

    public StudyPlotSaver(Study study, String exportPath, int studyNumber) {
        this.study = study;
        this.exportPath = exportPath;
        this.studyNumber = studyNumber;
    }

    public void save() {
        try {
            for (Map.Entry<String, Category> entry : study.getCategories().entrySet()) {
                if (entry.getValue() instanceof NumCategory) {
                    CategoryChart chart = createPlot((NumCategory) entry.getValue());
                    categoryCharts.add(chart);
                }
                if (entry.getValue() instanceof CorrelationCategory) {
                    XYChart chart = createPlot((CorrelationCategory) entry.getValue());
                    xyCharts.add(chart);
                }
                if (entry.getValue() instanceof SelectionCategory) {
                    CategoryChart chart = createPlot((SelectionCategory) entry.getValue());
                    categoryCharts.add(chart);
                }
            }
        } catch (Exception e) {
            LOG.error("Could not create Plot!", e);
        }

        final List<BufferedImage> images = new ArrayList<>();
        for (CategoryChart categoryChart : categoryCharts) {
            images.add(BitmapEncoder.getBufferedImage(categoryChart));
        }
        for (XYChart xyChart : xyCharts) {
            images.add(BitmapEncoder.getBufferedImage(xyChart));
        }

        final BufferedImage img = createImage(images);

        try {
            File outputfile = new File(exportPath + "plot" + studyNumber + ".png");
            ImageIO.write(img, "png", outputfile);
        } catch (IOException e) {
            // handle exception
        }
    }

    private BufferedImage createImage(List<BufferedImage> images) {
        final int rowLength = (int) Math.sqrt(images.size());
        final int columnLength = images.size() / rowLength;
        final BufferedImage img = new BufferedImage(
                plotWidth * rowLength, columnLength * plotHeight,
                BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2d = img.createGraphics();

        for (int i = 0; i < images.size(); i++) {
            int xPos = i % rowLength;
            int yPos = i / rowLength;
            g2d.drawImage(images.get(i), xPos * plotWidth, yPos * plotHeight, null);
        }

        return img;
    }

    private CategoryChart createPlot(NumCategory cat) {
        List<Double> data = new ArrayList<>(cat.getDoubleResults().length);
        for (int i = 0; i < cat.getDoubleResults().length; i++) {
            data.add(cat.getDoubleResults()[i]);
        }

        CategoryChart chart = new CategoryChartBuilder()
                .width(plotWidth).height(plotHeight)
                .title(cat.getName())
                .build();
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAvailableSpaceFill(1);
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        //chart.getStyler().setSeriesColors(new Color[]{Color.BLUE});
        int bins = getBins(cat);
        Histogram histogram = new Histogram(data, bins, cat.getMin(), cat.getMax());
        chart.addSeries("data", roundList(histogram.getxAxisData()), histogram.getyAxisData());

        return chart;
    }

    private int getBins(NumCategory cat) {
        return 5; //TODO
    }

    private XYChart createPlot(CorrelationCategory cat) {
        if (cat.getDoubleResults().length != cat.getOrigin().getDoubleResults().length)
            throw new IllegalArgumentException("Category sizes have to be equal.");

        List<Double> dataY = new ArrayList<>();
        List<Double> dataX = new ArrayList<>();
        for (int i = 0; i < cat.getDoubleResults().length; i++) {
            if (cat.getDoubleResults()[i] != cat.getMissingValue()) {
                if (cat.getOrigin().getDoubleResults()[i] != cat.getOrigin().getMissingValue()) {
                    dataY.add(cat.getOrigin().getDoubleResults()[i]);
                    dataX.add(cat.getDoubleResults()[i]);
                }
            }
        }

        XYChart chart = new XYChartBuilder()
                .width(plotWidth).height(plotHeight)
                .title(cat.getName() + "-" + cat.getOrigin().getName() + "-Correlation")
                .build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.addSeries("data", dataX, dataY);

        return chart;
    }

    private CategoryChart createPlot(SelectionCategory cat) {
        List<String> data = new ArrayList<>(cat.getStringResults().length);
        for (int i = 0; i < cat.getStringResults().length; i++) {
            data.add(cat.getStringResults()[i]);
        }

        CategoryChart chart = new CategoryChartBuilder()
                .width(plotWidth).height(plotHeight)
                .title(cat.getName())
                .build();
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAvailableSpaceFill(1);
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        //chart.getStyler().setSeriesColors(new Color[]{Color.BLUE});

        List<String> values = new ArrayList<>(cat.getSelection());
        values.add(cat.getMissingValue());
        List<Integer> yData = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            yData.add(0);
        }
        for (String s : cat.getStringResults()) {
            yData.set(values.indexOf(s), yData.get(values.indexOf(s)) + 1);
        }
        chart.addSeries("data", values, yData);

        return chart;
    }

    private List<Double> roundList(List<Double> list) {
        List<Double> newList = new ArrayList<>(list.size());
        for (Double aDouble : list) {
            newList.add((double) Math.round(aDouble));
        }
        return newList;
    }

}
