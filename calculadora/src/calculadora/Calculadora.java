package calculadora;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.Event;

public class Calculadora {


	public static void main(String[] args) {

		masterFrame marcoCentrado = new masterFrame();


	}//--------------------->Main>----------------------

}//---------------->Class Calculadora>--------------------




class masterFrame extends JFrame{

	public masterFrame(){
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		Toolkit mipantalla=Toolkit.getDefaultToolkit();
		Dimension tamañoPantalla=mipantalla.getScreenSize();

		int alturaPantalla = tamañoPantalla.height;
		int anchoPantalla = tamañoPantalla.width;

		setSize(anchoPantalla/2, alturaPantalla/2);

		setLocation(anchoPantalla/4, alturaPantalla/4);

		setTitle("...::: calculadora :::...");


		//-------------------->icono de pantalla>------------------

		Image miIcono=mipantalla.getImage("icono.jpg");
		setIconImage(miIcono);


		Lamina_calculadora Lamina = new Lamina_calculadora();
		add(Lamina);


	}//--------------------->fin constructor>-----------------

}//----------------->fin clase marco>------------------


class Lamina_calculadora extends JPanel {

	public Lamina_calculadora(){

		setLayout(new BorderLayout());

		//-------------------->creación de menu de opciones>------------------

		JMenuBar menu=new JMenuBar();

		JMenu archivo = new JMenu("Archivo");
		JMenu edit = new JMenu("Editar");
		JMenu vista = new JMenu("Vista");
		JMenu ayuda = new JMenu("Ayuda");

		JMenuItem acercade = new JMenuItem("Acerca de ");

		JRadioButtonMenuItem cientifica = new JRadioButtonMenuItem("Cientifica");
		JRadioButtonMenuItem normal = new JRadioButtonMenuItem("Normal");

		vista.add(cientifica);
		vista.add(normal);

		ayuda.add(acercade);

		menu.add(archivo);
		menu.add(edit);
		menu.add(vista);
		menu.add(ayuda);

		add(menu, BorderLayout.NORTH);

		//--------------------->pantalla de resultados calculadora>----------------------
		principio = true;

		pantalla=new JTextField("0");
		pantalla.setEnabled(false);
		add(pantalla, BorderLayout.CENTER);
		Botones=new JPanel();

		BotonC=new JPanel();

		ActionListener Insertar = new InsertarNumero();
		ActionListener Orden = new AccionOrden();

		//-------------------->botones estandar>-----------------------


		Botones.setLayout(new GridLayout(8,4));

		ponerBoton("%",Orden);
		ponerBoton("Ln",Orden);
		ponerBoton("Log",Orden);
		ponerBoton("pi",Orden);

		ponerBoton("e",Orden);
		ponerBoton("^2",Orden);
		ponerBoton("^n",Orden);
		ponerBoton("mod",Orden);

		ponerBoton("tan",Orden);
		ponerBoton("cos",Orden);
		ponerBoton("sin",Orden);
		ponerBoton("n!",Orden);

		ponerBoton("←",Orden);
		ponerBoton("C",Orden);
		ponerBoton("AC",Orden);
		ponerBoton("sqrt",Orden);

		ponerBoton("7",Insertar);
		ponerBoton("8",Insertar);
		ponerBoton("9",Insertar);
		ponerBoton("/",Orden);

		ponerBoton("4",Insertar);
		ponerBoton("5",Insertar);
		ponerBoton("6",Insertar);
		ponerBoton("*",Orden);

		ponerBoton("1",Insertar);
		ponerBoton("2",Insertar);
		ponerBoton("3",Insertar);
		ponerBoton("+",Orden);

		ponerBoton("0",Insertar);
		ponerBoton(".",Insertar);
		ponerBoton("=",Orden);
		ponerBoton("-",Orden);

		add(Botones, BorderLayout.SOUTH);

		ultimaOperacion = "=";
	}

	//---------------------->metodo para agregar botones estandar>--------------------

	private void ponerBoton(String rotulo, ActionListener oyente){

		JButton boton = new JButton(rotulo);

		boton.addActionListener(oyente);

		Botones.add(boton);

	}//----------------->fin metodo>---------------------


	//-------------------------->eventos>-----------------

	private class InsertarNumero implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			String entrada=e.getActionCommand();

			if(principio){
				pantalla.setText("");

				principio = false;
			}

			pantalla.setText(pantalla.getText() + entrada);
		}



	}//---------------------->fin eventos botones númericos>----------------------




	private class AccionOrden implements ActionListener{

		public void actionPerformed(ActionEvent e) {


			String operacion = e.getActionCommand();

			calcular(Double.parseDouble(pantalla.getText()));

			ultimaOperacion = operacion;

			principio = true;

		}




		public void calcular(double x){

			if(ultimaOperacion.equals("+")){

				resultado+=x;


			}

			else if(ultimaOperacion.equals("-")){

				resultado-=x;

			}

			else if(ultimaOperacion.equals("*")){

				resultado*=x;

			}

			else if(ultimaOperacion.equals("/")){

				resultado/=x;

			}

			else if(ultimaOperacion.equals("sqrt")){

				resultado = Math.sqrt(x);
			}

			else if(ultimaOperacion.equals("C")){

				x = 0;

			}

			else if(ultimaOperacion.equals("AC")){

				x = 0;
				resultado = x;
			}

			else if(ultimaOperacion.equals("^n")){

				resultado = Math.pow(resultado, x);

			}

			else if(ultimaOperacion.equals("^2")){

				resultado = x*x;

			}

			else if(ultimaOperacion.equals("mod")){

				resultado = resultado % x;

			}

			else if(ultimaOperacion.equals("n!")){

				for(int i = 1; i < x; i++){

					resultado *=i;
				}
			}

			else if(ultimaOperacion.equals("sin")){
				resultado = Math.sin(x);
			}

			else if(ultimaOperacion.equals("cos")){
				resultado = Math.cos(x);
			}

			else if(ultimaOperacion.equals("tan")){
				resultado = Math.tan(x);
			}

			else if(ultimaOperacion.equals("%")){

				resultado = (resultado * x) / 100;

			}

			else if(ultimaOperacion.equals("Ln")){

				resultado = Math.log(x);

			}

			else if(ultimaOperacion.equals("Log")){

				resultado = Math.log10(x);

			}

			else if(ultimaOperacion.equals("pi")){

				resultado = Math.PI;

			}

			else if(ultimaOperacion.equals("e")){

				resultado = Math.E;

			}



			else if(ultimaOperacion.equals("=")){

				resultado=x;
			}

			pantalla.setText("" + resultado);


		}

	}//--------------------------->fin acción listener operaciones matemáticas>----------------------

	private JPanel BotonC;

	private JPanel Botones;

	private JTextField pantalla;

	private boolean principio;

	private double resultado;

	private String ultimaOperacion;

}
