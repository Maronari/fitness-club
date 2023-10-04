package ru.mirea.app.fitness_club.ORM;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members_have_inbody_analyses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberInbodyAnalyses {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_member")
    private Members member;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "id_inbody_analyses")
    private InbodyAnalyses inbodyAnalyses;
}
