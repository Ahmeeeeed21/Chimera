<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Feedback
 *
 * @ORM\Table(name="feedback")
 * @ORM\Entity
 */
class Feedback
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
     * @ORM\Column(name="sujet", type="string", length=100, nullable=false)
     */
    private $sujet;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=255, nullable=false)
     */
    private $type;

    /**
     * @var int
     *
     * @ORM\Column(name="nbr_etoiles", type="integer", nullable=false)
     */
    private $nbrEtoiles;

    /**
     * @var int|null
     *
     * @ORM\Column(name="OtherID", type="integer", nullable=true, options={"default"="NULL"})
     */
    private $otherid = NULL;

    /**
     * Feedback constructor.
     * @param int $id
     * @param string $sujet
     * @param string $description
     * @param string $type
     * @param int $nbrEtoiles

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
    public function getSujet(): ?string
    {
        return $this->sujet;
    }

    /**
     * @param string $sujet
     */
    public function setSujet(string $sujet): void
    {
        $this->sujet = $sujet;
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
     * @return int
     */
    public function getNbrEtoiles(): ?int
    {
        return $this->nbrEtoiles;
    }

    /**
     * @param int $nbrEtoiles
     */
    public function setNbrEtoiles(int $nbrEtoiles): void
    {
        $this->nbrEtoiles = $nbrEtoiles;
    }

    /**
     * @return int|null
     */
    public function getOtherid(): ?int
    {
        return $this->otherid;
    }

    /**
     * @param int|null $otherid
     */
    public function setOtherid(?int $otherid): void
    {
        $this->otherid = $otherid;
    }


}
