package de.holube.study.io;

import de.holube.study.Study;
import de.holube.study.category.Category;
import de.holube.study.category.CorrelationCategory;
import de.holube.study.category.NumberCategory;
import de.holube.study.category.SelectionCategory;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchart.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.*;

@Slf4j
public class StudyPlotSaver {

    private static final int PLOT_WIDTH = 400;
    private static final int PLOT_HEIGHT = 300;

    private final Study study;
    private final String exportPath;
    private final int studyNumber;

    private final List<CategoryChart> categoryCharts = new ArrayList<>();
    private final List<XYChart> xyCharts = new ArrayList<>();

    public StudyPlotSaver(@NonNull Study study, @NonNull String exportPath, int studyNumber) {
        this.study = study;
        this.exportPath = exportPath;
        this.studyNumber = studyNumber;
    }

    public void save() {
        try {
            for (Map.Entry<String, Category<?>> entry : study.getCategories().entrySet()) {
                if (entry.getValue() instanceof NumberCategory c) {
                    CategoryChart chart = createPlot(c);
                    categoryCharts.add(chart);
                }
                if (entry.getValue() instanceof CorrelationCategory c) {
                    XYChart chart = createPlot(c);
                    xyCharts.add(chart);
                }
                if (entry.getValue() instanceof SelectionCategory c) {
                    CategoryChart chart = createPlot(c);
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
                PLOT_WIDTH * rowLength, columnLength * PLOT_HEIGHT,
                BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2d = img.createGraphics();

        for (int i = 0; i < images.size(); i++) {
            int xPos = i % rowLength;
            int yPos = i / rowLength;
            g2d.drawImage(images.get(i), xPos * PLOT_WIDTH, yPos * PLOT_HEIGHT, null);
        }

        return img;
    }

    private CategoryChart createPlot(NumberCategory cat) {
        List<Double> data = new ArrayList<>(cat.getResults().length);
        Collections.addAll(data, cat.getResults());

        CategoryChart chart = new CategoryChartBuilder()
                .width(PLOT_WIDTH).height(PLOT_HEIGHT)
                .title(cat.getName())
                .build();
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAvailableSpaceFill(1);
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        int bins = getBins(cat);
        Histogram histogram = new Histogram(data, bins, cat.getMin(), cat.getMax());
        chart.addSeries("data", roundList(histogram.getxAxisData()), histogram.getyAxisData());

        return chart;
    }

    private int getBins(NumberCategory cat) {
        return 5; //TODO
    }

    private XYChart createPlot(CorrelationCategory cat) {
        if (cat.getResults().length != cat.getOrigin().getResults().length)
            throw new IllegalArgumentException("Category sizes have to be equal.");

        List<Double> dataY = new ArrayList<>();
        List<Double> dataX = new ArrayList<>();
        for (int i = 0; i < cat.getResults().length; i++) {
            if ((!Objects.equals(cat.getResults()[i], cat.getMissingValue())) &&
                    (!Objects.equals(cat.getOrigin().getResults()[i], cat.getOrigin().getMissingValue()))) {
                dataY.add(cat.getOrigin().getResults()[i]);
                dataX.add(cat.getResults()[i]);
            }
        }

        XYChart chart = new XYChartBuilder()
                .width(PLOT_WIDTH).height(PLOT_HEIGHT)
                .title(cat.getName() + "-" + cat.getOrigin().getName() + "-Correlation")
                .build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.addSeries("data", dataX, dataY);

        return chart;
    }

    private CategoryChart createPlot(SelectionCategory cat) {
        CategoryChart chart = new CategoryChartBuilder()
                .width(PLOT_WIDTH).height(PLOT_HEIGHT)
                .title(cat.getName())
                .build();
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAvailableSpaceFill(1);
        chart.getStyler().setChartBackgroundColor(Color.WHITE);

        List<String> values = new ArrayList<>(cat.getOptions());
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
