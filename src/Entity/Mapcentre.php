<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Mapcentre
 *
 * @ORM\Table(name="mapcentre")
 * @ORM\Entity
 */
class Mapcentre
{
    /**
     * @var float
     *
     * @ORM\Column(name="lat", type="float", precision=10, scale=0, nullable=false)
     */
    private $lat;

    /**
     * @var float
     *
     * @ORM\Column(name="lng", type="float", precision=10, scale=0, nullable=false)
     */
    private $lng;

    /**
     * @var \Centre
     *
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     * @ORM\OneToOne(targetEntity="Centre")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_centre", referencedColumnName="id")
     * })
     */
    private $idCentre;


}
