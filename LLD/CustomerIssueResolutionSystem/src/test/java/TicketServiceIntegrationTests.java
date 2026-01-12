import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.learning.lld.controller.AgentController;
import org.learning.lld.controller.TicketController;
import org.learning.lld.model.TicketType;
import org.learning.lld.repository.*;
import org.learning.lld.service.AgentService;
import org.learning.lld.service.TicketService;
import org.learning.lld.strategy.IAgentAssignmentStrategy;
import org.learning.lld.strategy.RandomAgentAssignmentStrategy;

import java.util.Arrays;
import java.util.UUID;

public class TicketServiceIntegrationTests {
    private TicketController ticketController;
    private AgentController agentController;
    private TicketService ticketService;
    private AgentService agentService;
    private IAgentRepository agentRepository;
    private IWorkLogRepository workLogRepository;
    private ITicketRepository ticketRepository;
    private ICustomerRepository customerRepository;
    private IAgentAssignmentStrategy agentAssignmentStrategy;


    @Before
    public void setup() {
        this.agentRepository = new InMemoryAgentRepository();
        this.workLogRepository = new InMemoryWorkLogRepository();
        this.ticketRepository = new InMemoryTicketRepository();
        this.customerRepository = new InMemoryCustomerRepository();
        this.agentService = new AgentService(this.agentRepository, this.workLogRepository);
        this.agentAssignmentStrategy = new RandomAgentAssignmentStrategy();
        this.ticketService = new TicketService(this.agentService, this.ticketRepository, this.customerRepository, this.agentAssignmentStrategy);
        this.ticketController = new TicketController(this.ticketService);
        this.agentController = new AgentController(this.agentService);
    }

    @Test
    public void createAndAssignTicket() {
        createAgents();
        String t1 = this.ticketController.createTicket(
                UUID.randomUUID().toString(),
                TicketType.PAYMENT_RELATED.toString(),
                "Unable to make UPI payments",
                "Unable to make UPI payments",
                "reddy@gmail.com"
        );

        String agentId = this.ticketController.assignTicket(t1);

        String t2 = this.ticketController.createTicket(
                UUID.randomUUID().toString(),
                TicketType.PAYMENT_RELATED.toString(),
                "Unable to make UPI payments",
                "Unable to make UPI payments",
                "reddy1@gmail.com"
        );

        String agentId1 = this.ticketController.assignTicket(t2);

        String t3 = this.ticketController.createTicket(
                UUID.randomUUID().toString(),
                TicketType.PAYMENT_RELATED.toString(),
                "Unable to make UPI payments",
                "Unable to make UPI payments",
                "reddy1@gmail.com"
        );

        String agentId3 = this.ticketController.assignTicket(t3);
    }


    void createAgents() {
        this.agentController.createAgent("a1", "a1@gmail.com", Arrays.asList(new String[]{TicketType.MUTUALFUND_RELATED.name(), TicketType.PAYMENT_RELATED.name(), TicketType.GOLD_RELATED.name()}));
        this.agentController.createAgent("a2", "a2@gmail.com", Arrays.asList(new String[]{TicketType.PAYMENT_RELATED.name(), TicketType.GOLD_RELATED.name()}));
        this.agentController.createAgent("a3", "a3@gmail.com", Arrays.asList(new String[]{TicketType.INSURANCE_RELATED.name()}));
    }
}
