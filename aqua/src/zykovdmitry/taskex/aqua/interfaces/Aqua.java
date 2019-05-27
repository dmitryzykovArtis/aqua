package zykovdmitry.taskex.aqua.interfaces;

import java.util.List;

public interface Aqua {
	
	//
	//Интерфейс аквариума.
	//
	
	//Построение фундамента аквариума согласно полученного списка.
	void setFoundation(List<Integer> list);
	
	//Заполнение формы аквариума жидкостью.
	void fill();
	
	//Получение списка столбцов аквариума.
	List<Column> getColumnList();
	
	//Подсчет количества кубов жидкости в аквариуме.
	long solve();

}
