/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.BaseForm;
import com.mycompany.myapp.services.ServiceCentre;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author amira
 */
public class StatCentre extends BaseForm {

    public StatCentre(Resources res) {
        super.addSideMenu(res);
        createPieChartForm();
    }
    
     private DefaultRenderer buildCategoryRenderer(int[] colors) {

    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(65);
    renderer.setLegendTextSize(0);
    renderer.setLabelsColor(ColorUtil.rgb(62, 217, 48));
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}


protected CategorySeries buildCategoryDataset(String title) {
    CategorySeries series = new CategorySeries(title);
         ServiceCentre sc= new ServiceCentre();
         Map<String,Double> s=sc.StatCentres();
        // System.out.println(s.entrySet());
        for (Map.Entry x :s.entrySet())
        {
            series.add(x.getKey().toString(),(Double)x.getValue());
        }

    return series;
}

public void createPieChartForm() {

    // Generate the values
    //double[] values = new double[]{12, 14, 11, 10, 19};
   setTitle("Nbr services par Centre");
    // Set up the renderer
    int[] colors = new int[]{ColorUtil.rgb(241, 162, 245), ColorUtil.rgb(144, 243, 245), ColorUtil.rgb(110, 240, 201), ColorUtil.rgb(240, 197, 110), ColorUtil.rgb(149, 132, 245)};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
   
    r.setGradientStart(0, ColorUtil.rgb(241, 162, 245));
    r.setGradientStop(0, ColorUtil.rgb(144, 243, 245));
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Nombre des services par Centre"), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    //Form f = new Form("Budget");
    setLayout(new BorderLayout());
    //addComponent(BorderLayout.CENTER,new Label("Nombre des services par centre"));
    addComponent(BorderLayout.CENTER, c);
   // return f;

}

    
}
