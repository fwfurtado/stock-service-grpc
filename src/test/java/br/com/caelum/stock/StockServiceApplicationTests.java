package br.com.caelum.stock;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.caelum.stock.domain.StockItem;
import br.com.caelum.stock.grpc.ItemRequest;
import br.com.caelum.stock.grpc.ItemResponse;
import br.com.caelum.stock.grpc.StockServiceGrpc;
import br.com.caelum.stock.grpc.StockServiceGrpc.StockServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lognet.springboot.grpc.autoconfigure.GRpcServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceApplicationTests {

    @Autowired
    private GRpcServerProperties properties;

    @Autowired
    private Converter<ItemResponse, StockItem> converter;

    @Test
    public void shouldReturnAStockItemOfARQCode() {
        var channel = ManagedChannelBuilder.forAddress("localhost", properties.getRunningPort()).usePlaintext().build();

        var service = StockServiceGrpc.newBlockingStub(channel);

        var response = service.findBy(ItemRequest.newBuilder()
            .setCode("ARQ")
            .build());

        var stockItem = converter.convert(response);

        System.out.println(stockItem);

        channel.shutdown();

        assertThat(stockItem)
            .hasFieldOrPropertyWithValue("code", "ARQ")
            .hasFieldOrPropertyWithValue("quantity", 30);
    }
}
