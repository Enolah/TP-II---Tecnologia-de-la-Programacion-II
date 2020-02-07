package tp.p1.logic;

public class GameObjectBoard {
	private GameObject[] objects;
	private int currentObjects;
	private int ancho;
	private int alto;
	private int max=20;

	public GameObjectBoard(int width, int height) {
		currentObjects = 0;
		objects = new GameObject[max];
		ancho = width;
		alto = height;
	}

	private int getCurrentObjects() {
		return currentObjects;
	}

	public void add(GameObject object) {
		// TODO implementar array dinamico cuando se ocupa todo el array
		// sumarle 20 cuando llegue al maximo
		if (currentObjects < max)
			objects[currentObjects++] = object;
		else {
			objects[max*2]= objects[max]; 
			objects[currentObjects++] = object;
		}

	}

	private GameObject getObjectInPosition(int x, int y) {

		GameObject resultado = null;
		for (int i = 0; i < currentObjects; i++)  {
			if (objects[i].isOnPosition(x, y)) {
				resultado = objects[i];
			}
		}
		return resultado;
	}

	private int getIndex(int x, int y) {
		int resultado = 0;
		for (int i = 0; i < currentObjects; i++) {
			if (objects[i].isOnPosition(x, y)) {
				resultado = i;
			}
		}
		return resultado;
	}

	public void remove(GameObject object) {
		for (int i = 0; i < currentObjects; i++) {
			if (objects[i].equals(object))
				objects[i] = null;
		}
	}

	public void update() {
		//obj.move
		for (int i = 0; i < currentObjects; i++) {
			objects[i].move();
		}
		//obj!=other) object.perfomAttack()
	}

	private void checkAttacks(GameObject object) {
		// TODO implement
	
	}

	public void computerAction() {
		for (int i = 0; i < currentObjects; i++) {
			objects[i].computerAction();
		}
	}

	private void removeDead() {
		int i = 0;
		for (int j = 0; j < objects.length; j++)
			if (objects[j] != null || objects[j].isAlive())
				if (i != j)
					objects[i++] = objects[j];
				else
					i++;
		for (; i < objects.length; i++)
			objects[i] = null;
	}
	
	public void doDamage(int x, int y, int damage){
		for (int i = 0; i < currentObjects; i++) {
			if(objects[i].isOnPosition(x, y))
				objects[i].getDamage(damage);
		}
	}

	public String toString(int x, int y) {
		String s=" ";
		GameObject a=getObjectInPosition(x,y);
		if (a!= null)

			s=a.toString();
		return s;
	}

}
