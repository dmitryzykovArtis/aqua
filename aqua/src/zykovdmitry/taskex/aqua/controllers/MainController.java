package zykovdmitry.taskex.aqua.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import zykovdmitry.taskex.aqua.interfaces.Aqua;
import zykovdmitry.taskex.aqua.interfaces.AquaReader;
import zykovdmitry.taskex.aqua.interfaces.imp.DefaultAqua;
import zykovdmitry.taskex.aqua.interfaces.imp.DefaultAquaReader;

public class MainController {
	
	//
	//Для изображения аквариума используется StackedBarChart вне метода нажатия кнопки инициализируем две серии данных.
	//
	
	private XYChart.Series<String, Number> dataSeries1;
	private XYChart.Series<String, Number> dataSeries2;
	
	//
	//Подключаем FXML 
	//
	
	@FXML
	private Button firstButton;
	@FXML
	private TextField firstTextField;
	@FXML
	private Label outputLabel;
	@FXML
	private StackedBarChart<String, Number> aquaStack;
	
	public void initialize() {
		
		
		//Подготавливаем диаграмму для ввода данных
		
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Столбцы");
		
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Высота кубиков");
		
		 dataSeries1 = new XYChart.Series<String, Number>();
		 dataSeries2 = new XYChart.Series<String, Number>();
		 
		dataSeries1.setName("Earth");
		dataSeries2.setName("Water");
		

		aquaStack.getData().add(dataSeries1);
		aquaStack.getData().add(dataSeries2);
		
		
	}
	
	
	
	public void firstButtonAction(ActionEvent actionevent) {
		
		//Подключаем реализации аквариума и считывателя
		
		 AquaReader reader = new DefaultAquaReader();
		 Aqua aqua = new DefaultAqua();
		
		try{
			
		//Считываем строку из интерфейса
		reader.read(firstTextField.getText());
		
		//Проверяем строку на корректность
		if(reader.isCorrect()) {
			
			//Заполняем фундамент аквариума
			aqua.setFoundation(reader.getList());
			
			//В случае корректной строки, проверяем значение количества стобцов (1 и 2 столбца невозможно заполнить, но нужно отобразить)
			if(reader.getList().size() > 2) {
			
				//В случае 3 и больше кол-ва столбцов заполняем аквариум  и выводим количество кубиков воды в аквариуме.
				aqua.fill();
				outputLabel.setText("Расчетное значение кубиков воды: " + aqua.solve() + " шт.");

			}else{
				
				//Если значение столбцов 2 и меньше, то аквариум не заполняется, выводится 0 кубиков воды.
				outputLabel.setText("Расчетное значение кубиков воды: 0 шт.");	
			}
			
			//В случае повторного нажатия на кнопку, диаграмма отчищается от старых значений
			dataSeries1.getData().clear();
			dataSeries2.getData().clear();
			
			
			
			//В серии данных заносятся значения из списка (Аквариума). (Серия 1 - фундамент, Серия 2 - заполнения)
			
			aquaStack.disableProperty().set(true);
			
			for (int i = 0; i < aqua.getColumnList().size(); i++) {
				
				System.out.println(i);
				dataSeries1.getData().add(new XYChart.Data<String, Number>("" + (i+1), aqua.getColumnList().get(i).getFoundation()));
				dataSeries2.getData().add(new XYChart.Data<String, Number>("" + (i+1), aqua.getColumnList().get(i).getFilling()));
			}
			
			aquaStack.disableProperty().set(false);
			

		}else{
			
			//В случае некорректности введеной строки выводится причина
			outputLabel.setText(reader.getReason());
		}
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	

	
}
