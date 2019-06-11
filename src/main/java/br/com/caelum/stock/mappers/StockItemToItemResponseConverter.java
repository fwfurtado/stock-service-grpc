package br.com.caelum.stock.mappers;

import br.com.caelum.stock.domain.StockItem;
import br.com.caelum.stock.grpc.ItemResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StockItemToItemResponseConverter implements Converter<StockItem, ItemResponse> {

    @Override
    public ItemResponse convert(StockItem source) {
        return ItemResponse.newBuilder()
            .setCode(source.getCode())
            .setQuantity(source.getQuantity())
            .build();
    }
}
