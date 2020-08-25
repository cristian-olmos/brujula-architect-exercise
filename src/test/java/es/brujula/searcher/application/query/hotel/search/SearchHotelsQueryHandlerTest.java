package es.brujula.searcher.application.query.hotel.search;

import es.brujula.searcher.domain.hotel.model.Hotel;
import es.brujula.searcher.domain.hotel.repository.HotelRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class SearchHotelsQueryHandlerTest {

    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String HOTEL_ID = "hotelId";
    private static final String CATEGORY_ID = "1";
    private static final String SERVICE_ID = "serviceId";
    @InjectMocks
    private SearchHotelsQueryHandler searchHotelsQueryHandler;

    @Mock
    private HotelRepository hotelRepository;

    @Test
    public void create_validParams_ok() {
        SearchHotelsQuery searchHotelsQuery = getSearchHotelsQuery();
        doReturn(getHotelsResponse()).when(hotelRepository).search(any());

        Collection<Hotel> handle = searchHotelsQueryHandler.handle(searchHotelsQuery);
        Assert.assertEquals(handle.size(), 1);
        Assert.assertEquals(handle.stream().findFirst().get().name(), NAME);
        Assert.assertEquals(handle.stream().findFirst().get().address(), ADDRESS);
        Assert.assertEquals(handle.stream().findFirst().get().id(), HOTEL_ID);
        Assert.assertEquals(handle.stream().findFirst().get().category(), CATEGORY_ID);
    }

    @Test
    public void create_notFound_emptyList() {
        SearchHotelsQuery searchHotelsQuery = getSearchHotelsQuery();
        doReturn(getEmptyResponse()).when(hotelRepository).search(any());

        Collection<Hotel> handle = searchHotelsQueryHandler.handle(searchHotelsQuery);
        Assert.assertEquals(handle.size(), 0);
    }

    private List<Hotel> getHotelsResponse() {
        return Arrays.asList(Hotel.create(HOTEL_ID, NAME, ADDRESS, CATEGORY_ID));
    }

    private List<Hotel> getEmptyResponse() {
        return new ArrayList<>();
    }

    private SearchHotelsQuery getSearchHotelsQuery() {
        return new SearchHotelsQuery(NAME, Arrays.asList(CATEGORY_ID), Arrays.asList(SERVICE_ID));
    }
}
