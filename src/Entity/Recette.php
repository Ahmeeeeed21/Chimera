<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;
use Vich\UploaderBundle\Mapping\Annotation as Vich;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Recette
 *
 * @ORM\Table(name="recette")
 * @ORM\Entity
 * @Vich\Uploadable()
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
     * @Assert\NotBlank(message="nom is required")
     *
     */
    private $nomrecette;

    /**
     * @var string
     *
     * @ORM\Column(name="typeRecette", type="string", length=32, nullable=false)
     *  @Assert\Choice({"Entrée", "Dessert", "Amuse bouche","Sauce","Accompagnement","Boisson"},message="Choose a valid type.")
     *
     */
    private $typerecette;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="text", length=65535, nullable=false)
     * @Assert\NotBlank(message="description is required")
     * @Assert\Length(min=3)
     *
     */
    private $description;
    /**
     * @var File|null
     * @Vich\UploadableField(mapping="recettes", fileNameProperty="Image")
     */
    private $image_File;

    /**
     * @var string | null
     *
     * @ORM\Column(name="image", type="text", length=65535, nullable=false)
     *
     */
    private $image;


    /**
     * @ORM\Column(type="datetime")
     *
     * @var \DateTimeInterface|null
     */
    private $updatedAt;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=64, nullable=false)
     *
     */
    private $etat;

    /**
     * @var int|null
     *
     * @ORM\Column(name="idcoach", type="integer", nullable=true, options={"default"="NULL"})
     */
    private $idcoach = NULL;


    /**
     * @return int
     */
    public function getIdrecette()
    {
        return $this->idrecette;
    }

    /**
     * @param int $idrecette
     */
    public function setIdrecette(int $idrecette)
    {
        $this->idrecette = $idrecette;
    }

    /**
     * @return string
     */
    public function getNomrecette()
    {
        return $this->nomrecette;
    }

    /**
     * @param string $nomrecette
     */
    public function setNomrecette(string $nomrecette)
    {
        $this->nomrecette = $nomrecette;
    }

    /**
     * @return string
     */
    public function getTyperecette()
    {
        return $this->typerecette;
    }

    /**
     * @param string $typerecette
     */
    public function setTyperecette(string $typerecette)
    {
        $this->typerecette = $typerecette;
    }

    /**
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription(string $description)
    {
        $this->description = $description;
    }

    /**
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * @param string $image
     */
    public function setImage(string $image)
    {
        $this->image = $image;
    }

    /**
     * @return \DateTime|null
     */
    public function getUpdatedAt()
    {
        return $this->updatedAt;
    }

    /**
     * @param \DateTime|null $updatedAt
     */
    public function setUpdatedAt($updatedAt)
    {
        $this->updatedAt = $updatedAt;
    }

    /**
     * @return string
     */
    public function getEtat()
    {
        return $this->etat;
    }

    /**
     * @param string $etat
     */
    public function setEtat(string $etat)
    {
        $this->etat = $etat;
    }

    /**
     * @return int|null
     */
    public function getIdcoach()
    {
        return $this->idcoach;
    }

    /**
     * @param int|null $idcoach
     */
    public function setIdcoach(?int $idcoach)
    {
        $this->idcoach = $idcoach;
    }

    public function getImageFile()
    {
        return $this->image_File;
    }

    /**
     * @param mixed $image_File
     */
    public function setImageFile($image_File)
    {
        $this->image_File = $image_File;
        if ($image_File) {
            $this->updatedAt = new \DateTime('now');
        }
    }

    public function __toString()
    {
        return $this->nomrecette;// TODO: Implement __toString() method.
    }

}
