<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210412205321 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE activite DROP FOREIGN KEY fk_eventAct');
        $this->addSql('ALTER TABLE activite CHANGE idEvent idEvent INT DEFAULT NULL');
        $this->addSql('ALTER TABLE activite ADD CONSTRAINT FK_B87555152C6A49BA FOREIGN KEY (idEvent) REFERENCES evenement (id)');
        $this->addSql('ALTER TABLE centre_service DROP prix');
        $this->addSql('ALTER TABLE centre_service RENAME INDEX id_centre TO IDX_A9FC1197E0975F98');
        $this->addSql('ALTER TABLE centre_service RENAME INDEX id_service TO IDX_A9FC11973F0033A2');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY fk_pub_phy');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY fk_pub_utilisateur');
        $this->addSql('ALTER TABLE commentaire CHANGE id_utilisateur id_utilisateur INT DEFAULT NULL, CHANGE id_publication id_publication INT DEFAULT NULL');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT FK_67F068BCB72EAA8E FOREIGN KEY (id_publication) REFERENCES publication_physique (id)');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT FK_67F068BC50EAE44 FOREIGN KEY (id_utilisateur) REFERENCES utilisateur (id)');
        $this->addSql('ALTER TABLE commentairepsy DROP FOREIGN KEY fk_pubpsy');
        $this->addSql('ALTER TABLE commentairepsy CHANGE id_publication2 id_publication2 INT DEFAULT NULL');
        $this->addSql('ALTER TABLE commentairepsy ADD CONSTRAINT FK_5AA6464F10623E80 FOREIGN KEY (id_publication2) REFERENCES publication_psy (id)');
        $this->addSql('ALTER TABLE logged CHANGE id id INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE publication_physique CHANGE id_coach id_coach INT DEFAULT NULL');
        $this->addSql('ALTER TABLE publication_psy DROP FOREIGN KEY fk_coach_psy');
        $this->addSql('ALTER TABLE publication_psy CHANGE id_coach id_coach INT DEFAULT NULL');
        $this->addSql('ALTER TABLE publication_psy ADD CONSTRAINT FK_A525BE20D1DC2CFC FOREIGN KEY (id_coach) REFERENCES utilisateur (id)');
        $this->addSql('ALTER TABLE reclamation CHANGE id_utilisateur id_utilisateur INT DEFAULT NULL');
        $this->addSql('ALTER TABLE rendezvous CHANGE id_centre id_centre INT DEFAULT NULL, CHANGE id_utilisateur id_utilisateur INT DEFAULT NULL, CHANGE id_service id_service INT DEFAULT NULL');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE activite DROP FOREIGN KEY FK_B87555152C6A49BA');
        $this->addSql('ALTER TABLE activite CHANGE idEvent idEvent INT NOT NULL');
        $this->addSql('ALTER TABLE activite ADD CONSTRAINT fk_eventAct FOREIGN KEY (idEvent) REFERENCES evenement (id) ON DELETE CASCADE');
        $this->addSql('ALTER TABLE centre_service ADD prix DOUBLE PRECISION NOT NULL');
        $this->addSql('ALTER TABLE centre_service RENAME INDEX idx_a9fc1197e0975f98 TO id_centre');
        $this->addSql('ALTER TABLE centre_service RENAME INDEX idx_a9fc11973f0033a2 TO id_service');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY FK_67F068BCB72EAA8E');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY FK_67F068BC50EAE44');
        $this->addSql('ALTER TABLE commentaire CHANGE id_publication id_publication INT NOT NULL, CHANGE id_utilisateur id_utilisateur INT DEFAULT NULL');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT fk_pub_phy FOREIGN KEY (id_publication) REFERENCES publication_physique (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT fk_pub_utilisateur FOREIGN KEY (id_utilisateur) REFERENCES utilisateur (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE commentairepsy DROP FOREIGN KEY FK_5AA6464F10623E80');
        $this->addSql('ALTER TABLE commentairepsy CHANGE id_publication2 id_publication2 INT NOT NULL');
        $this->addSql('ALTER TABLE commentairepsy ADD CONSTRAINT fk_pubpsy FOREIGN KEY (id_publication2) REFERENCES publication_psy (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE logged CHANGE id id INT NOT NULL');
        $this->addSql('ALTER TABLE publication_physique CHANGE id_coach id_coach INT DEFAULT NULL');
        $this->addSql('ALTER TABLE publication_psy DROP FOREIGN KEY FK_A525BE20D1DC2CFC');
        $this->addSql('ALTER TABLE publication_psy CHANGE id_coach id_coach INT DEFAULT NULL');
        $this->addSql('ALTER TABLE publication_psy ADD CONSTRAINT fk_coach_psy FOREIGN KEY (id_coach) REFERENCES utilisateur (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE reclamation CHANGE id_utilisateur id_utilisateur INT NOT NULL');
        $this->addSql('ALTER TABLE rendezvous CHANGE id_centre id_centre INT NOT NULL, CHANGE id_utilisateur id_utilisateur INT NOT NULL, CHANGE id_service id_service INT NOT NULL');
    }
}
