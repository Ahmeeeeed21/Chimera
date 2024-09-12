<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Ingredientrecette
 *
 * @ORM\Table(name="ingredientrecette")
 * @ORM\Entity
 */
class Ingredientrecette
{
    /**
     * @var int
     *
     * @ORM\Column(name="idRecette", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     */
    private $idrecette;

    /**
     * @var int
     *
     * @ORM\Column(name="idIngredient", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     */
    private $idingredient;

    /**
     * @var float
     *
     * @ORM\Column(name="Quantite", type="float", precision=10, scale=0, nullable=false)
     */
    private $quantite;


}
