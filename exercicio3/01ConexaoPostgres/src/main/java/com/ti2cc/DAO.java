package com.ti2cc;

import java.sql.*;

public class DAO {
    private Connection conexao;

    DAO(){
        conexao = null;
    }

    public boolean conectar(){
        String driveName = "org.postgresql.Driver";
        String serverName = "localhost";
        String mydatabase = "postgres";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
        String username = "ti2cc";
        String password = "123";
        boolean status = false;

        try {
            Class.forName(driveName);
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao == null);
            System.out.println("Conectado");
        } catch (ClassNotFoundException e) {
            System.err.println("Erro de conexao: Classe nao encontrada");
        } catch (SQLException e){
            System.err.println("Erro de conexao: A conexao com o SQL falhou" + e.getMessage());
        } catch (Exception e){
            System.err.println("Erro de conexao: " + e.getMessage());
        }

        return status;
    }

    public boolean close(){
        boolean status = false;

        try {
            conexao.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return status;
    }

    public boolean inserirEmpresa(Empresa empresa){
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO empresa (nome, ceo, cnpj, endereco, dataAbertura, situacaoCadastral, capital)"
            + "Values (" + empresa.getNome() + ", " + empresa.getCeo() + ", '" + empresa.getCnpj() + "', '" + empresa.getEndereco() + "', " + empresa.getDataAbertura() + ", "
            + empresa.getSituacaoCadastral() + ", " + empresa.getCapital()+ ");");
            st.close();
            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return status;
    }

    public boolean atualizarEmpresa(Empresa empresa){
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE empresa SET nome = " + empresa.getNome() + ", " + empresa.getCeo() + ", '" + empresa.getCnpj() + "', '" + empresa.getEndereco() + "', " + empresa.getDataAbertura() + ", " + empresa.getSituacaoCadastral() + ", " + empresa.getCapital();
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return status;
    }

    public boolean excluirEmpresa(String cnpj){
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM empresa WHERE cnpj = " + cnpj);
            st.close();
            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public Empresa[] getEmpresas(){
        Empresa[] empresas = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM empresa");

            if(rs.next()){
                rs.last();
                empresas = new Empresa[rs.getRow()];
                rs.beforeFirst();

                for(int i = 0; rs.next(); i++){
                    empresas[i] = new Empresa(rs.getString("nome"), rs.getString("ceo"), rs.getString("cnpj"), rs.getString("endereco"), rs.getString("dataAbertura"), rs.getString("situacaoCadastral"), rs.getDouble("capital"));
                }
                st.close();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return empresas;
    }
}