package br.com.caelum.stock.repository;

import static br.com.caelum.stock.domain.BookCode.*;
import static java.util.Optional.ofNullable;

import br.com.caelum.stock.domain.BookCode;
import br.com.caelum.stock.domain.StockItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class StockRepository {

    private static final Map<BookCode, StockItem> database = new HashMap<>();

    static {
        database.computeIfAbsent(ARQ, code -> new StockItem(code.name(), 30));
        database.computeIfAbsent(SOA, code -> new StockItem(code.name(), 10));
        database.computeIfAbsent(WEB, code -> new StockItem(code.name(), 20));
    }

    public Optional<StockItem> findByCode(BookCode code) {
        return ofNullable(database.get(code));
    }
}
