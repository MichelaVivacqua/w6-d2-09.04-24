package michelavivacqua.demo.services;

import michelavivacqua.demo.entities.Autore;
import michelavivacqua.demo.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class AutoriService {
    private List<Autore> autoriList = new ArrayList<>();

    public List<Autore> getAutoriList(){
        return this.autoriList;
    }

    public Autore saveAutore (Autore body){
        Random rndm = new Random();
        body.setId(rndm.nextInt(1,10000));
        this.autoriList.add(body);
        return body;
    }

    public Autore findById(int id){
        Autore found = null;
        for(Autore autore: this.autoriList){
            if(autore.getId() == id) found = autore;
        }
        if(found == null) throw new NotFoundException(id);
        else return found;
    }

    public Autore findByIdAndUpdate(int id, Autore updatedAutore){
        Autore found = null;
        for(Autore autore: this.autoriList){
            if(autore.getId() == id) {
                found = autore;
                found.setNome(updatedAutore.getNome());
                found.setCognome(updatedAutore.getCognome());
            }
        }
        if(found == null) throw new NotFoundException(id);
        else return found;
    }

    public void findByIdAndDelete(int id){
        Iterator<Autore> iterator = this.autoriList.iterator();

        while(iterator.hasNext()){
            Autore current = iterator.next();
            if(current.getId() == id){
                iterator.remove();
            }
        }

    }

}
