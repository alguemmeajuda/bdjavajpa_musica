/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

package persistencia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import vo.Musica;

public class MusicaDAO {

    EntityManager em;

    public MusicaDAO() {
        em = EntityManagerProvider.getEM();
    }

    public void salva(Musica m) {
        em.getTransaction().begin();
        if (m.getCodigo() == 0) {
            em.persist(m);
        } else {
            em.merge(m);
        }
        em.getTransaction().commit();
    }

    public Musica localiza(int codigo) {
        Musica m = em.find(Musica.class, codigo);
        return m;
    }

    public void exclui(Musica m) {
        em.getTransaction().begin();
        em.remove(m);
        em.getTransaction().commit();
    }

    public List<Musica> pesquisa() {
        Query q = em.createQuery("select m from Musica as m order by m.titulo");
        List<Musica> lista = q.getResultList();
        return lista;
    }

    public List<Musica> pesquisa(String titulo) {
        Query q = em.createNativeQuery("select * from musica where titulo like ? order by titulo", Musica.class);
        q.setParameter(1, '%' + titulo + '%');
        List<Musica> lista = q.getResultList();
        return lista;
    }
}

