package br.com.caelum.stock.service;

import br.com.caelum.stock.domain.BookCode;
import br.com.caelum.stock.domain.StockItem;
import br.com.caelum.stock.grpc.ItemRequest;
import br.com.caelum.stock.grpc.ItemResponse;
import br.com.caelum.stock.grpc.StockServiceGrpc;
import br.com.caelum.stock.repository.StockRepository;
import io.grpc.stub.StreamObserver;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class StockService extends StockServiceGrpc.StockServiceImplBase {

    private final StockRepository repository;

    public StockService(StockRepository repository) {
        this.repository = repository;
    }

    @Override
    public void findBy(ItemRequest request, StreamObserver<ItemResponse> responseObserver) {
        var requestCode = request.getCode();
        var code = BookCode.valueOf(requestCode);

        Optional<ItemResponse> itemResponse = repository.findByCode(code).map(this::buildBy);

        itemResponse
            .ifPresentOrElse(responseObserver::onNext, () -> responseObserver.onError(new NoSuchElementException()));

        responseObserver.onCompleted();

    }

    private ItemResponse buildBy(StockItem item) {
        return ItemResponse.newBuilder()
            .setCode(item.getCode())
            .setQuantity(item.getQuantity())
            .build();
    }
}
