package zykovdmitry.taskex.aqua.interfaces;

public abstract class Block {
	
	
	//
	//Абстрактный класс блока в аквариуме.
	//
	
	//Количество блоков определенного типа.
	private int count;
	
	
	
	//Конструктор блока, по умолчании количество блоков = 0.
	public Block() {
		count = 0;
	}

	
	//Сеттер количества блоков.
	public void setCount(int count) {
		this.count = count;
		
	}

	
	//Геттер количества блоков.
	public int getCount() {
		return count;
	}

	//Инкрементирует количество блоков на один.
	public void incrCount() {
		count++;
	}

	/*
	//Каждый блок возвращает свой цвет.
	public abstract String getColour();
	*/
}
