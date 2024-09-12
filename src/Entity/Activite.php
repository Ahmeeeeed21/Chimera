<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Activite
 *
 * @ORM\Table(name="activite", indexes={@ORM\Index(name="fk_eventAct", columns={"idEvent"})})
 * @ORM\Entity
 */
class Activite
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
     * @ORM\Column(name="nom", type="string", length=70, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=50, nullable=false)
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="Description", type="string", length=255, nullable=false)
     */
    private $description;

    /**
     * @var int
     *
     * @ORM\Column(name="duree", type="integer", nullable=false)
     */
    private $duree;

    /**
     * @var \Evenement
     *
     * @ORM\ManyToOne(targetEntity="Evenement")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idEvent", referencedColumnName="id")
     * })
     */
    private $idevent;

    private $nomEvent;

    /**
     * Activite constructor.
     * @param int $id
     * @param string $nom
     * @param string $type
     * @param string $description
     * @param int $duree
     * @param \Evenement $idevent
     */
    public function __construct(int $id, string $nom, string $type, string $description, int $duree, \Evenement $idevent)
    {
        $this->id = $id;
        $this->nom = $nom;
        $this->type = $type;
        $this->description = $description;
        $this->duree = $duree;
        $this->idevent = $idevent;
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
    public function getNom(): string
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
     * @return string
     */
    public function getType(): string
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
    public function getDescription(): string
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
     * @return int
     */
    public function getDuree(): int
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
     * @return \Evenement
     */
    public function getIdevent(): \Evenement
    {
        return $this->idevent;
    }

    /**
     * @param \Evenement $idevent
     */
    public function setIdevent(\Evenement $idevent): void
    {
        $this->idevent = $idevent;
    }

    /**
     * @return mixed
     */
    public function getNomEvent()
    {
        return $this->nomEvent;
    }

    /**
     * @param mixed $nomEvent
     */
    public function setNomEvent($nomEvent): void
    {
        $this->nomEvent = $nomEvent;
    }




}
