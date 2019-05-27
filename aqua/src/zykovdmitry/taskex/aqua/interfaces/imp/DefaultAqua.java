package zykovdmitry.taskex.aqua.interfaces.imp;

import java.util.ArrayList;
import java.util.List;

import zykovdmitry.taskex.aqua.interfaces.Aqua;
import zykovdmitry.taskex.aqua.interfaces.Column;

public class DefaultAqua implements Aqua {
	
	//
	//Первичная реализация методов интерфейса Aqua.
	//
	
	//Переменная позволяет определить и хранит максимальную высоту фундамента заданную пользователем
	private int maxFoundationValue = Integer.MIN_VALUE;
	
	//Список столбов в аквариуме
	private ArrayList<Column> columnList = new ArrayList<>();   

	
	
	//Построение фундамента аквариума согласно полученного списка.
	//Для каждого значения в списке строится столбец с фундаментом высотой указанной в значении.
	//Попутно ищется максимальное значение высоты фундамента
	
	@Override
	public void setFoundation(List<Integer> list) {
		
		for(int foundationValue: list) {
		
			if(foundationValue > maxFoundationValue) {
				maxFoundationValue = foundationValue;
			}
	
			columnList.add(new DefaultColumn(foundationValue));
		}
	}
	
	
	//Построенный фундамент заполняется водой согласно заданной в задаче логике
	//За каждый i проход заполняется один слой воды, до максимального значения высоты фундамента (высота воды не может быть выше фунд.)
	//Для каждого j столбца проверяется не вытечет ли из него вода, т.е. есть ли справа и слева материал с большей высотой.
	//isLeftBigger смотрит левый столбец, isRightBigger смотрит правый столбец, в случае равенства функция заходит в рекурсию после которой
	//отмечает все просмотренные столбцы, чтобы не повторять операцию снова.
	//В случае если высота фундамента меньше 1 столбец не заполняется.
	
	@Override
	public void fill() {
		for(int i=1; i < maxFoundationValue; i++) {
			
			for(int j=1; j < columnList.size() - 1; j++) {
				System.out.println(i+" "+ j);
				if(isLeftBigger(j) && isRightBigger(j) && (columnList.get(j).getFoundation() > 0)) {
					columnList.get(j).incrFilling();
				}
			}
		}
	}

	
	//Возвращает аквариум в виде списка из столбцов.
	@Override
	public List<Column> getColumnList() {
		return columnList;
	}
	
	//Проверяет больше ли материала (фундамент+заполнение) у левого от проверяемого столбца.
	private boolean isLeftBigger(int index){
		return (columnList.get(index - 1).getSumm() > columnList.get(index).getSumm());
	}
	
	//Проверяет больше ли материала (фундамент+заполнение) у правого от проверяемого столбца, если встречает равенство, то заходит в рекурсию
	//Выходя из рекурсии отмечает все проверенные столбцы (-1 справа меньше, 1 справа больше, 0 не проверен), чтобы не повторять рекурсивные
	//действия на следующей итерации снова.
	private boolean isRightBigger(int index) {
		
		//Проверяет, если столбец проверен, то берет предыдущее решение (1 справа больше) или (-1 справа меньше) и снимает метку в 0
		
		if(columnList.get(index).isNextRightIsBigger() == 1) {
			columnList.get(index).setNextRightIsBigger(0);
			return true;
		}
		
		if(columnList.get(index).isNextRightIsBigger() == -1) {
			columnList.get(index).setNextRightIsBigger(0);
			return false;
		}
		//
		
	
		
		
		//Проверяет, если заполнения следущего столбца равно данному столбцу, то проверяет существует ли послеследующий (проверяет конец списка)
		//Если последний столбец равен предпоследнему, тогда ставится false, так как вода будет выливаться. Если это не конец списка то 
		// вызывает данную функцию для следующего столбца. Что в случае плоской поверхности дает рекурсию. По выходу из которой
		// отмечает проверенный столбец, чтобы не проверять его снова (в случае плоской поверхности).
		if(columnList.get(index + 1).getSumm() == columnList.get(index).getSumm()) {
			
			if(index + 1 == columnList.size() - 1) {
				return false;
			}
			
			if(isRightBigger(index + 1)) {
				columnList.get(index+1).setNextRightIsBigger(1);
				return true;
			}else {
				columnList.get(index+1).setNextRightIsBigger(-1);
				return false;
			}
		}
		
		//Под конец, если столбец не отмечен и значение заполнения не равно следующему, выполняется сравнение заполнения со следующим столбцом. 
		
		if(columnList.get(index + 1).getSumm() > columnList.get(index).getSumm()) {
			return true;
		}else {
			return false;
		}
		
		
	}

	//Подсчитывает количество кубиков воды во всех столбцах аквариума. Используем long т.к. 65534х65534 не помещается в int.
	@Override
	public long solve() {
		long summFilling = 0;
		for (int i=1; i < columnList.size() - 1; i++) {
			summFilling += columnList.get(i).getFilling();
		}
		return summFilling;
	}
}
