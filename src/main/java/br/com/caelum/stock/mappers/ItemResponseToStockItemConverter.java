package br.com.caelum.stock.mappers;

import br.com.caelum.stock.domain.StockItem;
import br.com.caelum.stock.grpc.ItemResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ItemResponseToStockItemConverter implements Converter<ItemResponse, StockItem> {

    @Override
    public StockItem convert(ItemResponse source) {
        return new StockItem(source.getCode(), source.getQuantity());
    }
}
