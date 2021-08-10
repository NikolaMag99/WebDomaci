package rs.raf.demo.repository.quote;

import rs.raf.demo.models.Quote;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryQuoteRepository implements IQuoteRepository {
    private static final List<Quote> quotes = new CopyOnWriteArrayList<>();


    static {
        quotes.add( new Quote(1, "Miki", "Web programiranje", "Razumevanje dinamičkog kreiranja veb sadržaja, pojma sesije i načina praćenja sesije.", new ArrayList<>(), java.time.LocalDate.now()));
        quotes.add(new Quote(2,"Zika", "Napredno Web programiranje", "Upoznavanje sa konceptima naprednog programiranja aplikacija velikog obima (Enterprise aplikacija).",  new ArrayList<>(), java.time.LocalDate.now()));
        quotes.add(new Quote(3,"Djole", "Softversko inženjerstvo", "Upoznavanje sa savremenim metodama i principima softverskog inženjerstva.",  new ArrayList<>(), java.time.LocalDate.now()));
    }


    public List<Quote> all() {
        List<Quote> quoteList = new ArrayList<>();
        quotes.iterator().forEachRemaining(e -> {
            quoteList.add(e);
        });

        return quoteList;
    }

    public void insert(Quote quote) {
        quotes.add(quote);
    }

    @Override
    public void update(Quote quote) {
        for(Quote q: quotes) {
            if(q.getId() == quote.getId()) {
                quotes.remove(q);
                quotes.add(quote);
            }
        }
    }

    @Override
    public List<Quote> allPosts() {
        return null;
    }

    @Override
    public Quote find(int id) {
        for(Quote q: quotes) {
            if(q.getId() == id) {
                return q;
            }
        }
        return null;
    }

}
