package zykovdmitry.taskex.aqua.interfaces;

import java.util.List;

public interface AquaReader {
	
	//
	//Интерфейс считывателя строки, проверяет корректность переданной строки и отдает на выходе список целых чисел.
	//
	
	//Получает на входе строку, строка корректна преобразует ее в список.
	void read(String str);
	
	//Возвращает корректность строки.
	boolean isCorrect();
	
	//Возвращает полученную строку.
	List<Integer> getList();
	
	//Возвращает причину в случее не корректности строки, в случае корректности возвращает ОК.
	String getReason();
}
