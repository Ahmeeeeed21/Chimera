<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commentairepsy
 *
 * @ORM\Table(name="commentairepsy", indexes={@ORM\Index(name="fk_pub_utilisateur2", columns={"id_utilisateur"}), @ORM\Index(name="id_publication2", columns={"id_publication2"})})
 * @ORM\Entity
 */
class Commentairepsy
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
     * @ORM\Column(name="date", type="string", length=255, nullable=false)
     */
    private $date ;

    /**
     * @var string
     *
     * @ORM\Column(name="datem", type="string",  length=255, nullable=false, options={"default"="current_timestamp()"})
     */
    private $datem ;

    /**
     * @var string
     *
     * @ORM\Column(name="contenu", type="string", length=255, nullable=false)
     */
    private $contenu;

    /**
     * @var int|null
     *
     * @ORM\Column(name="id_utilisateur", type="integer", nullable=true, options={"default"="NULL"})
     */
    private $idUtilisateur = NULL;

    /**
     * @var \PublicationPsy
     *
     * @ORM\ManyToOne(targetEntity="PublicationPsy")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_publication2", referencedColumnName="id")
     * })
     */
    private $idPublication2;

    /**
     * Commentairepsy constructor.
     * @param int $id
     * @param string $date
     * @param string $datem
     * @param string $contenu
     * @param int|null $idUtilisateur
     * @param \PublicationPsy $idPublication2
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
    public function setDate(string $date): void
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
    public function setDatem(string $datem): void
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

    /**
     * @return \PublicationPsy
     */
    public function getIdPublication2(): \PublicationPsy
    {
        return $this->idPublication2;
    }

    /**
     * @param \PublicationPsy $idPublication2
     */
    public function setIdPublication2(PublicationPsy $idPublication2): void
    {
        $this->idPublication2 = $idPublication2;
    }


}
