package zykovdmitry.taskex.aqua.interfaces.imp;

import java.util.ArrayList;
import java.util.List;

import zykovdmitry.taskex.aqua.interfaces.AquaReader;

public class DefaultAquaReader implements AquaReader {
	private boolean correct = false;
	private List<Integer> list = new ArrayList<Integer>();
	private String reason = "ОК";

	//Проверяет корректность переданной строки, разбивает строку по разделителю пробелу. Затем каждое значение проверяет на корректность
	//диапазона и добавляет в список.
	//Затем проверяет корректность длины списка и correct выставляется в true.
	//
	//В случае некорректности при разбиении, переводе к целым числам, выходу за диапазоны вызывается соответствующее исключение
	//и список очищается.
	@Override
	public void read(String str) {
		correct = false;
		try{
			int value = -1;
			for (String strValue: str.split(" ")) {
				value = Integer.parseInt(strValue);
				
				if (value < 0 || value > 65535) throw new Exception("Значение одного из столбов вне диапазона");
				list.add(value);
			}
			
			
			if (list.size() < 1 || list.size() > 65535) throw new Exception("Некорректное количество столбов");
			correct = true;
			reason = "ОК";
		}catch(NumberFormatException e) {
			reason = "Некорректно заданы значения";
			list.clear();
		}catch(Exception e) {
			reason = e.getMessage();
			list.clear();
		}
		
	}

	

	//Передает значение корректности
	@Override
	public boolean isCorrect() {
		return correct;
	}

	//Передает полученный список
	@Override
	public List<Integer> getList() {
		return list;
	}

	
	//Передает причину некорректности или ОК в случае корректности.
	@Override
	public String getReason() {
		return reason;
	}
	
}
