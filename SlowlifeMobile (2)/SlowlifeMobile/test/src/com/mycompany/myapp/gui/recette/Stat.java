/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.recette;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.myapp.BaseForm;
import com.mycompany.myapp.services.GestionRegime.ServiceRecette;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Yassine
 */
public class Stat extends BaseForm{
/**
 * Creates a renderer for the specified colors.
 */
private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(32);
    renderer.setLegendTextSize(60);
    renderer.setLabelsColor(0x00000000);
    renderer.setMargins(new int[]{20, 30, 20, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param title
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);   
        series.add("Accepté ", values[0]);
        series.add("En Attente ", values[1]);
        series.add("Refusé ", values[2]);
       

    return series;
}

public Form createPieChartForm(){
    // Generate the values
    double[] values = new double[3] ;
    List<String> Etat = new ArrayList<>(Arrays.asList("Accepter","En Attente","Refuser"));
    System.out.println("size="+Etat.size());
    for(int i=0;i<Etat.size();i++){
         String eta=Etat.get(i);
         System.out.println(i +" "+ServiceRecette.getInstance().ReadRecettesByEtat(eta).size()+" "+Etat.get(i));
         values[i]=(double)ServiceRecette.getInstance().ReadRecettesByEtat(eta).size();
    }
   
    // Set up the renderer
    int[] colors = new int[]{ColorUtil.rgb(0, 255, 0),ColorUtil.YELLOW, ColorUtil.rgb(255, 0, 0)};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(40);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.GREEN);
    r.setGradientStop(0, ColorUtil.GREEN);
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Project Recette", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = new Form("Stat des etats des recettes", new BorderLayout());
    f.add(BorderLayout.CENTER, c);
    
    f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->{new HomeRecetteCoachForm().showBack();});
    return f;

}
}
