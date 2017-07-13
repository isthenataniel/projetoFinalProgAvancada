/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal.controles;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author root
 */
public class Controle<T> {
    
    protected static final Character DB_TRUE = 't';
    protected static final Character DB_FALSE = 'f';
    
    
    public Controle() {
    }
    
    public void gravar(T classe) {
        Session sessao = null;
        try {
            sessao = NewHibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.saveOrUpdate(classe);
            transacao.commit();
        } catch (Exception hibEx) {
            hibEx.printStackTrace();
        } finally {
            sessao.close();
        }
    } 
    
    public void atualizar(T classe) {
        Session sessao = null;
        try {
            sessao = NewHibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.update(classe);
            transacao.commit();
        } catch (Exception hibEx) {
            hibEx.printStackTrace();
        } finally {
            sessao.close();
        }
    }
    
    public List<T> buscaQuery(String sql) {
        List<T> resultado = new ArrayList();
        Session sessao = null;
        try {
            sessao = NewHibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            org.hibernate.Query query = sessao.createQuery(sql);
            resultado = query.list();
            transacao.commit();
        } catch (Exception hibEx) {
            hibEx.printStackTrace();
        } finally {
            sessao.close();
        }
        return resultado;
    }
    
    public void excluir(T classe) {
        Session sessao = null;
        try {
            sessao = NewHibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.delete(classe);
            transacao.commit();
        } catch (Exception hibEx) {
            hibEx.printStackTrace();
        } finally {
            sessao.close();
        }
    }
    
    public static ResultSet obtemConsulta(String sql, Object[] parametros) {
        ResultSet result = null;
        try {
            Connection conexao = Conexao.open();
            PreparedStatement stm = conexao.prepareStatement(sql);
            for (int i = 0; i < parametros.length; i++) {
                adicionaParametro(stm, parametros[i], i+1);
            }
            result = stm.executeQuery();
            Conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    private static void adicionaParametro(PreparedStatement stm, Object objeto, Integer index) throws SQLException {
        if (objeto == null || objeto.equals("")) {
            stm.setNull(index, 0);
        } else if (objeto instanceof String) {
            stm.setString(index, (String)objeto);
        } else if (objeto instanceof Integer) {
            stm.setInt(index, (Integer)objeto);
        } else if (objeto instanceof Date) {
            stm.setDate(index, new java.sql.Date(((Date)objeto).getTime()));
        } else if (objeto instanceof Character) {
            stm.setString(index, String.valueOf(objeto));
        } else if (objeto instanceof Double) {
            stm.setDouble(index, (Double)objeto);
        } else if (objeto instanceof Boolean) {
            stm.setBoolean(index, (Boolean)objeto);
        }
    }
    
    public static ResultSet obtemConsulta(String sql) {
        ResultSet result = null;
        try {
            Connection conexao = Conexao.open();
            Statement stm = conexao.createStatement();
            result = stm.executeQuery(sql);
            Conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public void executaQuery(String sql) {
        try {
            Connection conexao = Conexao.open();
            Statement stm = conexao.createStatement();
            stm.executeUpdate(sql);
            Conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Integer executaQuery(String sql, Object[] parametros) {
        Integer result = null;
        try {
            Connection conexao = Conexao.open();
            PreparedStatement stm = conexao.prepareStatement(sql);
            for (int i = 0; i < parametros.length; i++) {
                adicionaParametro(stm, parametros[i], i+1);
            }
            result = stm.executeUpdate();
            Conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
