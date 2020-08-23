package es.brujula.searcher.application.command.hotels.addservice;

import es.brujula.searcher.domain.hotel.exception.HotelNotFoundException;
import es.brujula.searcher.domain.hotel.model.Hotel;
import es.brujula.searcher.domain.hotel.repository.HotelRepository;
import es.brujula.searcher.domain.hotel.repository.HotelServiceRepository;
import es.brujula.searcher.domain.service.exception.ServiceNotFoundException;
import es.brujula.searcher.domain.service.model.Services;
import es.brujula.searcher.domain.service.repository.ServiceRepository;
import es.brujula.shared.CommandHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class AddServiceToHotelCommandHandler implements CommandHandler<AddServiceToHotelCommand> {

    private final HotelRepository hotels;
    private final ServiceRepository services;
    private final HotelServiceRepository hotelServices;

    public AddServiceToHotelCommandHandler(HotelRepository hotels, ServiceRepository services, HotelServiceRepository hotelServices) {
        this.hotels = hotels;
        this.services = services;
        this.hotelServices = hotelServices;
    }

    public void handle(final AddServiceToHotelCommand command) {

        this.ensureThatHotelExists(command.hotelId());
        this.ensureThatServiceExists(command.serviceId());

        this.hotelServices.add(command.hotelId(),
                command.serviceId());

    }

    private void ensureThatHotelExists(String id) {
        Optional<Hotel> hotel = this.hotels.byId(id);
        if (!hotel.isPresent()) {
            throw new HotelNotFoundException(id);
        }
    }

    private void ensureThatServiceExists(String id) {
        Optional<Services> hotel = this.services.byId(id);
        if (!hotel.isPresent()) {
            throw new ServiceNotFoundException(id);
        }
    }

}
