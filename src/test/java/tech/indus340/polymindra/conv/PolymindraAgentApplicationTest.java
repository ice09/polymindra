package tech.indus340.polymindra.conv;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
// Used for manual testing only.
@Disabled
class PolymindraAgentApplicationTest {

    @Autowired
    PolymindraAgent agent;

    @Test
    void should_advise_devs_on_microservices() {
        interact(agent, "Welcome to the Devs & Architect Assembly. Feel free to talk about any topic. Whenever you want to have assistance and coaching, you can just summon Polymindra and ask her opinion.");
        interact(agent, "User123: Hey, have you ever considered the benefits of transitioning to a microservices architecture?");
        interact(agent, "User456: Interesting thought. I'm leaning towards the monolithic approach. What's your take?");
        interact(agent, "User123: Well, User789 made a compelling case for microservices in our last meeting. Scalability is a key advantage—each service can be developed, deployed, and scaled independently.");
        interact(agent, "User456: True, but User789's argument doesn't consider the complexity it adds. User987 pointed out that managing a distributed system with multiple services can be a nightmare.");
        interact(agent, "User123: what does polymindra think of it?");
    }

    private static void interact(PolymindraAgent agent, String userMessage) {
        System.out.println("==========================================================================================");
        System.out.println("[User]: " + userMessage);
        System.out.println("==========================================================================================");
        String agentAnswer = agent.chat(userMessage);
        System.out.println("==========================================================================================");
        System.out.println("[Agent]: " + agentAnswer);
        System.out.println("==========================================================================================");
    }
}