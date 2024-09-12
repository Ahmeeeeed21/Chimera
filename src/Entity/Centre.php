<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Centre
 *
 * @ORM\Table(name="centre")
 * @ORM\Entity
 */
class Centre
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
     * @ORM\Column(name="lieu", type="string", length=120, nullable=false)
     */
    private $lieu;

    /**
     * @var string
     *
     * @ORM\Column(name="Description", type="string", length=255, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=255, nullable=false)
     */
    private $image;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=100, nullable=false)
     */
    private $type;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="h_deb", type="time", nullable=true, options={"default"="NULL"})
     */
    private $hDeb = 'NULL';

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="h_fin", type="time", nullable=true, options={"default"="NULL"})
     */
    private $hFin = 'NULL';

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="Service", inversedBy="idCentre")
     * @ORM\JoinTable(name="centre_service",
     *   joinColumns={
     *     @ORM\JoinColumn(name="id_centre", referencedColumnName="id")
     *   },
     *   inverseJoinColumns={
     *     @ORM\JoinColumn(name="id_service", referencedColumnName="id")
     *   }
     * )
     */
    private $idService;

    /**
     * Constructor
     */
    public function __construct()
    {
        $this->idService = new \Doctrine\Common\Collections\ArrayCollection();
    }

}
