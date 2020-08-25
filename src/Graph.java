import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph extends JFrame implements ActionListener {

    int age = 17;
    int weight = 120; //pounds
    String globalAllOutputs[][] = new String[500][5];
    JFreeChart monthLineChart;
    JFreeChart pieChartMonth;

    private static ArrayList <String[]>selectOutputs = new ArrayList <String[]>();
    private static JComboBox monthDropdown = new JComboBox(Cardio.months);

    private JButton back = new JButton("Back");

    private JButton timeViewButton = new JButton ("Year View");
    boolean monthView = true;


    private JPanel pieMonthPanel;
    private JPanel pieYearPanel;
    private JPanel lineMonthPanel;
    private JPanel lineYearPanel;



    XYSeries lineSeriesMonth;
    DefaultPieDataset pieDataSetMonth;
    DefaultPieDataset pieDataSetYear;

    private JLabel notGraph = new JLabel("You don't have enough data to display the graph!");
    private JLabel notGraph2 = new JLabel("Come back after doing this exercise more!");

    private JLabel notGraph3 = new JLabel("You don't have enough data to display the graph!");
    private JLabel notGraph4 = new JLabel("Come back after doing more cardio!");

    private Font font = new Font("Sylfaen", Font.BOLD, 60);
    private Font font2 = new Font("Sylfaen", Font.BOLD, 21);
    private Font font3 = new Font("Sylfaen", Font.PLAIN, 20);



    private String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};

    String singleMonth;
    //String allOutputs[][] ,ArrayList<String[]> data
    public Graph (String allOutputs[][], ArrayList<String[]> data) {


        User tempAgeWeightUser = CreateAccount.userArray.get(LoginScreen.currentUser);
        age = tempAgeWeightUser.getAge();
        weight = tempAgeWeightUser.getWeight();
        globalAllOutputs = allOutputs;

        setLayout(null);
        setBounds(0, 0, 1000, 600);

        monthDropdown.addActionListener(this);
        monthDropdown.setBounds(500, 500, 200, 50);
        add(monthDropdown);

        timeViewButton.addActionListener(this);
        timeViewButton.setBounds(300, 500, 200, 50);
        add(timeViewButton);

        singleMonth = "January";

        pieDataSetYear = new DefaultPieDataset();

        for (int x = 0; x < data.size(); x++) {

            String[] oneExercise = new String[5];
            oneExercise = data.get(x);

            for (int f = 0; f < oneExercise.length;f++) {
                //				System.out.println(oneExercise[f]);
            }

            int singleData = Integer.parseInt(oneExercise[4]);

            pieDataSetYear.setValue(oneExercise[2], singleData);

        }

        JFreeChart pieChartYear = ChartFactory.createPieChart("Time spent on Cardio", pieDataSetYear, true,true, true	);
        pieYearPanel = new ChartPanel(pieChartYear);

        add(pieYearPanel);
        pieYearPanel.setBounds(0, 0, 490, 500);
        pieYearPanel.setVisible(false);

        pieDataSetMonth = new DefaultPieDataset();

        findSpecificLineDataMonth(allOutputs, "January");
        addSpecificPieDataMonth();

        pieChartMonth = ChartFactory.createPieChart("Time spent on cardio in " + singleMonth, pieDataSetMonth, true,true, true	);
        pieMonthPanel = new ChartPanel(pieChartMonth);

        add(pieMonthPanel);
        pieMonthPanel.setBounds(0, 0, 490, 500);
        pieMonthPanel.setVisible(true);












        lineSeriesMonth = new XYSeries("Calories Burned");
        findSpecificLineDataMonth(allOutputs, "January");
        addSpecificLineDataMonth();

        XYSeriesCollection lineDataMonth = new XYSeriesCollection(lineSeriesMonth);
        monthLineChart = ChartFactory.createXYLineChart("Calories burned in " + singleMonth, "Day", "Calories Burned", lineDataMonth, PlotOrientation.VERTICAL, true, true, false);

        XYPlot linePlotMonth = (XYPlot)monthLineChart.getPlot();
        XYLineAndShapeRenderer lineRenderer = new XYLineAndShapeRenderer();
        lineRenderer.setPaint(Color.cyan);
        linePlotMonth.setRenderer(lineRenderer);
        linePlotMonth.setBackgroundPaint(Color.white);

        lineMonthPanel = new ChartPanel(monthLineChart);
        lineMonthPanel.setBounds(490, 0, 490, 500);
        add(lineMonthPanel);




        DefaultCategoryDataset caloriesYearDataSet = new DefaultCategoryDataset();

        int caloriesInMonth[] = new int[12];

        for (int x = 0; x < data.size(); x++) {

            String[] oneLine = new String[6];
            oneLine = data.get(x);
            double singleDist = Integer.parseInt(oneLine[3]);
            double singleTime = Integer.parseInt(oneLine[4]);
            double singleHRate= Integer.parseInt(oneLine[5]);
            double singleData = ((age * 0.2017) - (weight * 0.09036) + (singleHRate * .6309) - 55.0969) * (singleTime / 4.184);
            //			System.out.println(singleData);
            if (singleData < 0)
                singleData = 0;
            singleMonth = oneLine[0];

            for (int f = 0; f < months.length; f++) {

                if (months[f].equals(singleMonth)) {

                    caloriesInMonth[f] += singleData;
                    //					System.out.println(singleData);

                }

            }


        }

        for (int x = 0; x < 12; x++) {

            //			System.out.println(caloriesInMonth[x]/5 + "   HIhihiHIhi");

            caloriesYearDataSet.addValue(caloriesInMonth[x]/5, "", months[x].substring(0,3));


        }





        JFreeChart caloriesYearLineChart = ChartFactory.createLineChart("Calories Burned in the Year", "Month", "Calories Burned", caloriesYearDataSet);



        //		XYPlot linePlotYear = (XYPlot)caloriesYearLineChart.getPlot();
        //		XYLineAndShapeRenderer lineRendererYear = new XYLineAndShapeRenderer();
        //		lineRendererYear.setPaint(Color.cyan);
        //		linePlotYear.setRenderer(lineRendererYear);
        //		linePlotYear.setBackgroundPaint(Color.white);

        Plot linePlotYear = caloriesYearLineChart.getPlot();
        LineAndShapeRenderer lineRendererYear = new LineAndShapeRenderer();
        lineRendererYear.setPaint(Color.cyan);
        linePlotYear.setBackgroundPaint(Color.white);
        lineRendererYear.setStroke( new BasicStroke(10.0f));



        lineYearPanel = new ChartPanel(caloriesYearLineChart);
        lineYearPanel.setBounds(490, 0, 490, 500);
        add(lineYearPanel);


        notGraph.setBounds(490, 0, 490, 500);
        notGraph.setFont(font2);
        notGraph.setVisible(true);
        add(notGraph);

        notGraph2.setBounds(490, 30, 490, 500);
        notGraph2.setFont(font2);
        notGraph2.setVisible(true);
        add(notGraph2);

        notGraph3.setBounds(0, 0, 490, 500);
        notGraph3.setFont(font2);
        notGraph3.setVisible(true);
        add(notGraph3);

        notGraph4.setBounds(0, 30, 490, 500);
        notGraph4.setFont(font2);
        notGraph4.setVisible(true);
        add(notGraph4);


        //lineSeriesMonth
        //PieDataSetMonth


        //pieMonthPanel
        //lineMonthPanel;
        if (lineSeriesMonth.getItemCount() < 2) {
            lineMonthPanel.setVisible(false);
        }else {
            lineMonthPanel.setVisible(true);
        }

        if (pieDataSetMonth.getItemCount() < 1) {
            pieMonthPanel.setVisible(false);
        }else {
            pieMonthPanel.setVisible(true);
        }

        lineYearPanel.setVisible(false);

        setVisible(true);


        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setBorder(null);
        back.setFont(new Font("Segoe Script" , Font.PLAIN, 40));
        back.setForeground(Color.BLACK);
        back.setBounds(70, 490, 250, 75);
        add(back);
        back.addActionListener(this);

        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                back.setForeground(Color.BLACK);
                back.setFont(new Font("Segoe Script" , Font.PLAIN, 70));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                back.setForeground(Color.BLACK);
                back.setFont(new Font("Segoe Script" , Font.PLAIN, 40));
            }
        });



    }







    public void findSpecificLineDataMonth(String allOutputs[][], String find) {

        int selectingIndexPie = 0;
        for (int x = 0; x < Cardio.index; x++) {

            for (int y = 0; y < 5; y++) {

                if (allOutputs[x][y].equals(find)) {

                    //               System.out.println(allOutputs[x][y]);
                    selectOutputs.add(allOutputs[x]);
                    selectingIndexPie++;

                }

            }

        }

    }


    public void addSpecificLineDataMonth () {

        for (int x = 0; x < selectOutputs.size(); x++) {

            String[] oneLine = new String[6];
            oneLine = selectOutputs.get(x);
            double singleDist = Integer.parseInt(oneLine[3]);
            double singleTime = Integer.parseInt(oneLine[4]);
            double singleHRate= Integer.parseInt(oneLine[5]);
            double singleData = ((age * 0.2017) - (weight * 0.09036) + (singleHRate * .6309) - 55.0969) * (singleTime / 4.184);

            if (singleData < 0)
                singleData = 0;
            int singleDay = Integer.parseInt(oneLine[1]);
            singleMonth = oneLine[0];
            lineSeriesMonth.add(singleDay, singleData);

        }

    }


    public void addSpecificPieDataMonth () {


        for (int x = 0; x < selectOutputs.size(); x++) {

            String[] oneExercise = new String[5];
            oneExercise = selectOutputs.get(x);
            int singleData = Integer.parseInt(oneExercise[4]);

            pieDataSetMonth.setValue(oneExercise[2], singleData);

        }

    }





    @Override
    public void actionPerformed(ActionEvent e) {


        lineSeriesMonth.clear();
        selectOutputs.clear();
        String selectedMonth = (String) monthDropdown.getSelectedItem();
        findSpecificLineDataMonth(globalAllOutputs, selectedMonth);
        addSpecificLineDataMonth();

        monthLineChart.setTitle("Calories burned in " + selectedMonth);

        pieChartMonth.setTitle("Time spent on cardio in " + selectedMonth);

        pieDataSetMonth.clear();
        selectOutputs.clear();
        //selectedMonth = (String) monthDropdown.getSelectedItem();
        findSpecificLineDataMonth(globalAllOutputs, selectedMonth);
        addSpecificPieDataMonth();
        setTitle("Calories burned in " + selectedMonth);


        if (e.getSource() == back) {

            dispose();
            try {
                new Cardio();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (FontFormatException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }


        if (e.getSource() == timeViewButton) {



            if (monthView == false)
                monthView = true;
            else if (monthView == true)
                monthView = false;




        }

        if (monthView == false) {

            lineMonthPanel.setVisible(false);
            pieMonthPanel.setVisible(false);
            monthDropdown.setVisible(false);

            pieYearPanel.setVisible(true);
            lineYearPanel.setVisible(true);

            timeViewButton.setText("Month View");



            System.out.println(pieDataSetYear.getItemCount() + "           pie data set year");
            if (pieDataSetYear.getItemCount() < 1) {
                pieYearPanel.setVisible(false);
            }else {
                pieYearPanel.setVisible(true);
            }

        } else {

            lineMonthPanel.setVisible(true);
            pieMonthPanel.setVisible(true);
            monthDropdown.setVisible(true);

            pieYearPanel.setVisible(false);
            lineYearPanel.setVisible(false);

            timeViewButton.setText("Year View");

            System.out.println(lineSeriesMonth.getItemCount() + "       lineseriesmonth");
            System.out.println(pieDataSetMonth.getItemCount() + "       piedatasetmonth");

            if (lineSeriesMonth.getItemCount() < 2) {
                lineMonthPanel.setVisible(false);
            }else {
                lineMonthPanel.setVisible(true);
            }

            if (pieDataSetMonth.getItemCount() < 1) {
                pieMonthPanel.setVisible(false);
            }else {
                pieMonthPanel.setVisible(true);
            }
        }
    }
}