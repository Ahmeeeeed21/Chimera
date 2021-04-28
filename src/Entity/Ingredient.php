<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\Validator\Constraints as Assert;
use Vich\UploaderBundle\Mapping\Annotation as Vich;
/**
 * Ingredient
 *
 * @ORM\Table(name="ingredient")
 * @ORM\Entity(repositoryClass="App\Repository\IngredientRepository")
 * @Vich\Uploadable()
 */
class Ingredient
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
     * @ORM\Column(name="nom", type="string", length=255, nullable=false)
     * @Assert\NotBlank(message="nom is required")
     * @Assert\Length(min=3)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="Image", type="text", length=65535, nullable=false)
     * @Assert\NotBlank(message="image is required")
     */
    private $image;

    /**
     * @var File | Null
     * @Vich\UploadableField(mapping="ingredients", fileNameProperty="Image")
     */
    private $image_File;

    /**
     * @ORM\Column(type="datetime")
     *
     * @var \DateTimeInterface|null
     */
    private $updatedAt;

    /**
     * @var int
     * @Assert\GreaterThan(-1,message="les calories doit être positifs")
     * @Assert\LessThan(1000)
     * @Assert\NotBlank(message="Calorie is required")
     * @ORM\Column(name="calorie", type="integer", nullable=false)
     */
    private $calorie;

    /**
     * @var string
     * @ORM\Column(name="unite", type="string", length=32, nullable=false)
     * @Assert\Choice({"1 gramme", "100 gramme", "1 cl","100 cl","1 paquet","1 Càs"},message="Choose a valid unite.")
     */
    private $unite;

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
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * @param string $nom
     */
    public function setNom($nom)
    {
        $this->nom = $nom;
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
    public function setImage($image)
    {
        $this->image = $image;
    }

    /**
     * @return int
     */
    public function getCalorie()
    {
        return $this->calorie;
    }

    /**
     * @param int $calorie
     */
    public function setCalorie($calorie)
    {
        $this->calorie = $calorie;
    }

    /**
     * @return string
     */
    public function getUnite()
    {
        return $this->unite;
    }

    /**
     * @param string $unite
     */
    public function setUnite($unite)
    {
        $this->unite = $unite;
    }

    public function __toString()
    {
        return $this->nom;  // TODO: Implement __toString() method.
    }

    /**
     * @return mixed
     */
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

    /**
     * @return \DateTimeInterface|null
     */
    public function getUpdatedAt(): ?\DateTimeInterface
    {
        return $this->updatedAt;
    }

    /**
     * @param \DateTimeInterface|null $updatedAt
     */
    public function setUpdatedAt(?\DateTimeInterface $updatedAt): void
    {
        $this->updatedAt = $updatedAt;
    }




}
