package zykovdmitry.taskex.aqua.interfaces.imp;

import zykovdmitry.taskex.aqua.interfaces.Block;
import zykovdmitry.taskex.aqua.interfaces.Column;

public class DefaultColumn implements Column {
	
	//Указание типа блоков фундамента и заполнения
	private Block foundation = new Earth();
	private Block filling = new Water();
	private int nextRightIsBigger = 0;
	
	//Конструктор по умолчанию и с указанием блоков фундамента.
	public DefaultColumn() {
		foundation.setCount(0);
		filling.setCount(0);
	}
	
	//Конструктор с указанием блоков фундамента.
	public DefaultColumn(int count) {
		foundation.setCount(count);
		filling.setCount(0);
	}

	
	//Сеттер количества блоков фундамента.
	@Override
	public void setFoundation(int count) {
		foundation.setCount(count);
	}

	//Геттер количества блоков фундамента.
	@Override
	public int getFoundation() {
		return foundation.getCount();
	}

	//Сеттер количества блоков заполнения.
	@Override
	public void setFilling(int count) {
		filling.setCount(count);
		
	}

	//Инкремент заполнения.
	@Override
	public void incrFilling(){
		filling.incrCount();
	}

	//Геттер количества блоков заполнения.
	@Override
	public int getFilling() {
		return filling.getCount();
	}
	
	//Геттер количества блоков фундамента и заполнения.
		@Override
		public int getSumm() {
			return (getFoundation() + getFilling());
		}


	//Указывает находится ли справа фундмент с большим значением (для случая с длинной ровной поверхностью, чтобы для каждого отдельного столбца не смотреть в конец ряда)
	// -1 - меньше, 0 - значение не проверялось, 1 - больше (изначально был буллиан, но нужна трехвариантность)
	@Override
	public int isNextRightIsBigger() {
		return nextRightIsBigger;
	}
	
	//Устанавливает находится ли справа фундмент с большим значением.
	public void setNextRightIsBigger(int nextRightIsBigger) {
		this.nextRightIsBigger = nextRightIsBigger;
	}

	
	
	


}
