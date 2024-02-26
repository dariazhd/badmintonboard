package de.hawhamburg.inf.badmintonboard;

import de.hawhamburg.inf.badmintonboard.Models.Spieler;
import de.hawhamburg.inf.badmintonboard.Models.Team;
import java.util.LinkedList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan
@EnableJpaRepositories
public class ProjectbApplication {
        public static final LinkedList<Spieler> spielerList = new LinkedList<Spieler>();
        public static final LinkedList<Team> teamList = new LinkedList<Team>();
    
	public static void main(String[] args) {
		SpringApplication.run(ProjectbApplication.class, args);
	}

}
