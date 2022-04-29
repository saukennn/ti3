package com.ti2cc;

import java.util.Scanner;

public class Principal {
	
	//MENU
	public static int menuCase(Scanner entrada) {
    	int opcao;
    	System.out.print("---------------------MENU---------------------\n" + 
    	"1)Listar\n2)Inserir\n3)Excluir\n4)Atualizar\n5)Sair\ninsira aqui:  ");
    	opcao = entrada.nextInt();
    	return opcao;
    }
	//PREENCHER
	 public static Empresa getEmpresaInfo(Scanner scan) {
		 	String nome = new String();
	    	String ceo = new String();
	    	String situacaoCadastral = new String();
	    	String cnpj = new String();
	    	String endereco = new String();
	    	String dataAbertura = new String();
	    	double capital;
	    	
	    	System.out.print("Insira o Nome: ");
	    	nome = scan.nextLine();
	    	
	    	System.out.print("Insira o CNPJ da Empresa: ");
	    	cnpj = scan.nextLine();
	    	scan.nextLine();
	    	
	    	System.out.print("Insira o CEO: ");
	    	ceo = scan.nextLine();
	    	
	    	System.out.print("Insira o Endereço: ");
	    	endereco = scan.nextLine();

	    	System.out.print("Insira a Data de Abertura: ");
	    	dataAbertura = scan.nextLine();

	    	System.out.print("Insira a Situacao Cadastral: ");
	    	situacaoCadastral = scan.nextLine();

	    	System.out.print("Insira o Capital: ");
	    	capital = scan.nextDouble();
	    	

	    	Empresa empresa = new Empresa(nome, cnpj, ceo, situacaoCadastral, endereco, dataAbertura, capital);
	    	
	    	return empresa;
	    }
	
	
	public static void main(String[] args) {
		DAO dao = new DAO();
		dao.conectar();
		Scanner entrada = new Scanner(System.in);
		boolean fim = false;
		
		while(fim == false) {
			switch(menuCase(entrada)) {
				//Listar
				case 1:
					try {
						
						Empresa[] empresas = dao.getEmpresas();
						
						for(int i = 0; i < empresas.length ; i++) {
							System.out.println(empresas[i].toString());
						}					
					}	catch(Exception e) {
							System.out.println("==================Atencao==================\n Banco Vazio, porfavor insira novos intens\n==================\" ");
					}
					break;
					
				//Inserir
				case 2:
					
					Empresa empresaInserida = getEmpresaInfo(entrada);
					
					if(dao.inserirEmpresa(empresaInserida) == true) 
						System.out.println("Insercao bem Sucedida : " + empresaInserida.toString());
					else
						System.out.println("ERRO: nao foi possivel inserir a empresa...");
					break;
					
				//Excluir	
				case 3:
					
					System.out.println("Nome da Empresa que vai ser excluida: ");
					String nome = entrada.nextLine();
					
					if(dao.excluirEmpresa(nome) == true)
						System.out.println("Empresa excluida em exito!");
					else
						System.out.println("Erro ao excluir a empresa: " + nome);
					break;
					
				//Atualizar
				case 4:
					
					Empresa empresaAtualizada = getEmpresaInfo(entrada);
					
					if(dao.atualizarEmpresa(empresaAtualizada) == true)
	        			System.out.println("Atualizacao bem sucedida!: " + empresaAtualizada.toString());
	        		else
	        			System.out.println("ERRO: nao foi possivel atualizar a empresa...");
	        		break;
	        		
	        	//Sair
				case 5:
					fim = true;
					break;
					
				//Se o usuario digitar algo que nao e uma das opcoes retorna mensagem
				default: System.out.println("Opcao inexistente, porfavor insira novamente entre 1 e 5");
				}
		}
		
	}
}