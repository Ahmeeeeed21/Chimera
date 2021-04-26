<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Ingredientrecette
 *
 * @ORM\Table(name="ingredientrecette", indexes={@ORM\Index(name="FK_5CF2445259020862", columns={"idRecette"}), @ORM\Index(name="idIngredient", columns={"idIngredient"})})
 * @ORM\Entity
 */
class Ingredientrecette
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
     * @var float
     *
     * @ORM\Column(name="Quantite", type="float", precision=10, scale=0, nullable=false)
     */
    private $quantite;

    /**
     * @var \Recette
     *
     * @ORM\ManyToOne(targetEntity="Recette")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idRecette", referencedColumnName="idRecette")
     * })
     */
    private $idrecette;

    /**
     * @var \Ingredient
     *
     * @ORM\ManyToOne(targetEntity="Ingredient")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idIngredient", referencedColumnName="id")
     * })
     */
    private $idingredient;

    /**
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return float
     */
    public function getQuantite()
    {
        return $this->quantite;
    }

    /**
     * @param float $quantite
     */
    public function setQuantite($quantite)
    {
        $this->quantite = $quantite;
    }

    /**
     * @return \Recette
     */
    public function getIdrecette()
    {
        return $this->idrecette;
    }

    /**
     * @param \Recette $idrecette
     */
    public function setIdrecette($idrecette)
    {
        $this->idrecette = $idrecette;
    }

    /**
     * @return \Ingredient
     */
    public function getIdingredient()
    {
        return $this->idingredient;
    }

    /**
     * @param \Ingredient $idingredient
     */
    public function setIdingredient($idingredient)
    {
        $this->idingredient = $idingredient;
    }


}
