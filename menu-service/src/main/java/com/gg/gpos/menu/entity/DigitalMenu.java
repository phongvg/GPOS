package com.gg.gpos.menu.entity;

import com.speedfrwk.data.jpa.audit.AuditEntityListener;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString(exclude = {"coCategory"})
@Entity
@Table(name = "digital_menu")
@EntityListeners(AuditEntityListener.class)
public class DigitalMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"id\"", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"co_category_id\"", nullable = false)
    private CoCategory coCategory;

    @Column(name = "\"src_img\"", nullable = true)
    private String srcImg;

    @Column(name = "\"url_img\"", nullable = true)
    private String urlImg;

    @Column(name = "\"order_number\"", nullable = true)
    private Integer orderNumber;

    @Column(name = "\"name\"", nullable = true)
    private String name;

    @Column(name = "\"url\"", nullable = true)
    private String url;
}
