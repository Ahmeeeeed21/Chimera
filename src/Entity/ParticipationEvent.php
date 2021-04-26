<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * ParticipationEvent
 *
 * @ORM\Table(name="participation_event")
 * @ORM\Entity
 */
class ParticipationEvent
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
     * @var int
     *
     * @ORM\Column(name="idEvent", type="integer", nullable=false)
     */
    private $idevent;

    /**
     * @var int
     *
     * @ORM\Column(name="idUser", type="integer", nullable=false)
     */
    private $iduser;

    /**
     * ParticipationEvent constructor.
     * @param int $id
     * @param int $idevent
     * @param int $iduser
     */
    public function activite__construct(int $id, int $idevent, int $iduser)
    {
        $this->id = $id;
        $this->idevent = $idevent;
        $this->iduser = $iduser;
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
     * @return int
     */
    public function getIdevent(): int
    {
        return $this->idevent;
    }

    /**
     * @param int $idevent
     */
    public function setIdevent(int $idevent): void
    {
        $this->idevent = $idevent;
    }

    /**
     * @return int
     */
    public function getIduser(): int
    {
        return $this->iduser;
    }

    /**
     * @param int $iduser
     */
    public function setIduser(int $iduser): void
    {
        $this->iduser = $iduser;
    }


}
