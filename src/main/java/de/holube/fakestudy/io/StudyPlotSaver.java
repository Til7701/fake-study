package de.holube.fakestudy.io;

import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.study.category.Category;
import de.holube.fakestudy.study.category.CorrelationCategory;
import de.holube.fakestudy.study.category.NumberCategory;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchart.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class StudyPlotSaver {

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
                if (entry.getValue() instanceof NumberCategory) {
                    CategoryChart chart = createPlot((NumberCategory) entry.getValue());
                    categoryCharts.add(chart);
                }
                if (entry.getValue() instanceof CorrelationCategory) {
                    XYChart chart = createPlot((CorrelationCategory) entry.getValue());
                    xyCharts.add(chart);
                }
            }
        } catch (Exception e) {
            LOG.error("Could not create Plot!", e);
        }

        final int rowLength = (int) Math.sqrt(categoryCharts.size());

        for (int i = 0; i < categoryCharts.size(); i++) {
            try {
                BitmapEncoder.saveBitmap(categoryCharts.get(i), exportPath + "study" + studyNumber + "_plot" + i, BitmapEncoder.BitmapFormat.PNG);
               /* File outputfile = new File(exportPath + "study" + studyNumber + "_plot" + i + ".png");
                BufferedImage image = createImage(plotImageList.get(i));
                ImageIO.write(image, "png", outputfile);*/
            } catch (IOException e) {
                LOG.error("Could not save image", e);
            }
        }
        for (int i = 0; i < xyCharts.size(); i++) {
            try {
                BitmapEncoder.saveBitmap(xyCharts.get(i), exportPath + "study" + studyNumber + "_plot" + (i + categoryCharts.size()), BitmapEncoder.BitmapFormat.PNG);
               /* File outputfile = new File(exportPath + "study" + studyNumber + "_plot" + i + ".png");
                BufferedImage image = createImage(plotImageList.get(i));
                ImageIO.write(image, "png", outputfile);*/
            } catch (IOException e) {
                LOG.error("Could not save image", e);
            }
        }
    }

    private BufferedImage createImage(Image img) {
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bufferedImage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bufferedImage;
    }

    private CategoryChart createPlot(NumberCategory cat) {
        List<Double> data = new ArrayList<>(cat.getDoubleResults().length);
        for (int i = 0; i < cat.getDoubleResults().length; i++) {
            data.add(cat.getDoubleResults()[i]);
        }

        CategoryChart chart = new CategoryChartBuilder()
                .width(800).height(400)
                .title(cat.getName())
                .build();
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        Histogram histogram = new Histogram(data, 5, cat.getMin(), cat.getMax());
        chart.addSeries("data", roundList(histogram.getxAxisData()), histogram.getyAxisData());

        return chart;
    }

    private List<Double> roundList(List<Double> list) {
        List<Double> newList = new ArrayList<>(list.size());
        for (Double aDouble : list) {
            newList.add((double) Math.round(aDouble));
        }
        return newList;
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
                .width(800).height(400)
                .title(cat.getName())
                .build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.addSeries("data", dataX, dataY);

        return chart;
    }

}
