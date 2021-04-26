<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Recette
 *
 * @ORM\Table(name="recette")
 * @ORM\Entity
 */
class Recette
{
    /**
     * @var int
     *
     * @ORM\Column(name="idRecette", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idrecette;

    /**
     * @var string
     *
     * @ORM\Column(name="nomRecette", type="string", length=64, nullable=false)
     */
    private $nomrecette;

    /**
     * @var string
     *
     * @ORM\Column(name="typeRecette", type="string", length=32, nullable=false)
     */
    private $typerecette;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="text", length=65535, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="text", length=65535, nullable=false)
     */
    private $image;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=64, nullable=false)
     */
    private $etat;

    /**
     * @var int|null
     *
     * @ORM\Column(name="idcoach", type="integer", nullable=true, options={"default"="NULL"})
     */
    private $idcoach = NULL;


}
