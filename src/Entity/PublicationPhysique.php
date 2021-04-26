<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * PublicationPhysique
 *
 * @ORM\Table(name="publication_physique", indexes={@ORM\Index(name="id_coach", columns={"id_coach"})})
 * @ORM\Entity
 */
class PublicationPhysique
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
     * @ORM\Column(name="type", type="string", length=50, nullable=false)
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="img", type="string", length=300, nullable=false)
     */
    private $img;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=300, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=70, nullable=false)
     */
    private $nom;

    /**
     * @var int
     *
     * @ORM\Column(name="duree", type="integer", nullable=false)
     */
    private $duree;

    /**
     * @var int
     *
     * @ORM\Column(name="repetition", type="integer", nullable=false)
     */
    private $repetition;

    /**
     * @var \Utilisateur
     *
     * @ORM\ManyToOne(targetEntity="Utilisateur")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_coach", referencedColumnName="id")
     * })
     */
    private $idCoach;

    /**
     * PublicationPhysique constructor.
     * @param int $id
     * @param string $type
     * @param string $img
     * @param string $description
     * @param string $nom
     * @param int $duree
     * @param int $repetition

     */
    public function __construct()
    {

    }


    /**
     * @return int
     */
    public function getId(): int
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
    public function getType(): ?string
    {
        return $this->type;
    }

    /**
     * @param string $type
     */
    public function setType(string $type): void
    {
        $this->type = $type;
    }

    /**
     * @return string
     */
    public function getImg(): ?string
    {
        return $this->img;
    }

    /**
     * @param string $img
     */
    public function setImg(string $img): void
    {
        $this->img = $img;
    }

    /**
     * @return string
     */
    public function getDescription(): ?string
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription(string $description): void
    {
        $this->description = $description;
    }

    /**
     * @return string
     */
    public function getNom(): ?string
    {
        return $this->nom;
    }

    /**
     * @param string $nom
     */
    public function setNom(string $nom): void
    {
        $this->nom = $nom;
    }

    /**
     * @return int
     */
    public function getDuree(): ?int
    {
        return $this->duree;
    }

    /**
     * @param int $duree
     */
    public function setDuree(int $duree): void
    {
        $this->duree = $duree;
    }

    /**
     * @return int
     */
    public function getRepetition(): ?int
    {
        return $this->repetition;
    }

    /**
     * @param int $repetition
     */
    public function setRepetition(int $repetition): void
    {
        $this->repetition = $repetition;
    }



}
