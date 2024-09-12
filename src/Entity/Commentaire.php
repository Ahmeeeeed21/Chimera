<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commentaire
 *
 * @ORM\Table(name="commentaire", indexes={@ORM\Index(name="fk_pub_utilisateur", columns={"id_utilisateur"}), @ORM\Index(name="id_publication", columns={"id_publication"})})
 * @ORM\Entity
 */
class Commentaire
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="date", type="string", length=255, nullable=false, options={"default"="current_timestamp()"})
     */
    private $date;

    /**
     * @var string
     *
     * @ORM\Column(name="datem", type="string", length=255, nullable=false, options={"default"="current_timestamp()"})
     */
    private $datem ;

    /**
     * @var string
     *
     * @ORM\Column(name="contenu", type="string", length=255, nullable=false)
     */
    private $contenu;

    /**
     * @var \PublicationPhysique
     *
     * @ORM\ManyToOne(targetEntity="PublicationPhysique")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_publication", referencedColumnName="id")
     * })
     */
    private $idPublication;

    /**
     * @var  int|null
     *
     * @ORM\Column(name="id_utilisateur", type="integer", nullable=true, options={"default"="NULL"})
     */
    private $idUtilisateur;


    /**
     * Commentaire constructor.
     * @param int $id
     * @param string $date
     * @param string $datem
     * @param string $contenu
     * @param \PublicationPhysique $idPublication
     * @param int|null $idUtilisateur
     */
    public function __construct()
    {
    }


    /**
     * @return int
     */
    public function getId(): ?int
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId(int $id): void
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getDate(): ?string
    {
        return $this->date;
    }

    /**
     * @param string $date
     */
    public function setDate($date): void
    {
        $this->date = $date;
    }

    /**
     * @return string
     */
    public function getDatem(): ?string
    {
        return $this->datem;
    }

    /**
     * @param string $datem
     */
    public function setDatem($datem): void
    {
        $this->datem = $datem;
    }

    /**
     * @return string
     */
    public function getContenu(): ?string
    {
        return $this->contenu;
    }

    /**
     * @param string $contenu
     */
    public function setContenu(string $contenu): void
    {
        $this->contenu = $contenu;
    }

    /**
     * @return \PublicationPhysique
     */
    public function getIdPublication(): \PublicationPhysique
    {
        return $this->idPublication;
    }

    /**
     * @param \PublicationPhysique $idPublication
     */
    public function setIdPublication(PublicationPhysique $idPublication): void
    {
        $this->idPublication = $idPublication;
    }

    /**
     * @return int|null
     */
    public function getIdUtilisateur(): ?int
    {
        return $this->idUtilisateur;
    }

    /**
     * @param int|null $idUtilisateur
     */
    public function setIdUtilisateur(?int $idUtilisateur): void
    {
        $this->idUtilisateur = $idUtilisateur;
    }


}


