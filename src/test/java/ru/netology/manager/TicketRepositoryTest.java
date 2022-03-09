package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    private TicketManager manager = new TicketManager(new TicketRepository());
    private final Ticket first = new Ticket(1, 1000, "VKO", "DXB", 4);
    private final Ticket second = new Ticket(2, 6000, "DME", "TPE", 10);
    private final Ticket third = new Ticket(3, 3000, "SVO", "GOJ", 12);
    private final Ticket fourth = new Ticket(4, 4000, "DME", "TPE", 4);

    @Test
    void shouldSort() {
        Ticket[] expected = new Ticket[]{first, third, fourth, second};
        Ticket[] actual = new Ticket[]{third, first, second, fourth};

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindAndNoCoincidences() {
        manager.saveTicket(first);
        manager.saveTicket(second);
        manager.saveTicket(third);
        manager.saveTicket(fourth);


        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("DME", "GOJ");


        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindCoincidences() {
        manager.saveTicket(first);
        manager.saveTicket(second);
        manager.saveTicket(third);
        manager.saveTicket(fourth);


        Ticket[] expected = new Ticket[]{fourth, second};
        Ticket[] actual = manager.searchBy("DME", "TPE");


        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemove() {
        manager.saveTicket(first);
        manager.saveTicket(second);
        manager.saveTicket(third);
        manager.saveTicket(fourth);


        Ticket[] expected = new Ticket[]{first, third, fourth};
        Ticket[] actual = manager.removeByIdTickets(2);


        assertArrayEquals(expected, actual);
    }
}