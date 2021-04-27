<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Suivi
 *
 * @ORM\Table(name="suivi")
 * @ORM\Entity
 */
class Suivi
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
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     */
    private $date;

    /**
     * @var float
     * @Assert\GreaterThan(40)
     * @Assert\LessThan(220)
     * @ORM\Column(name="poids", type="float", precision=10, scale=0, nullable=false)
     * @Assert\NotBlank(message="poids is required")
     */
    private $poids;

    /**
     * @var float
     * @Assert\GreaterThan(100)
     * @Assert\LessThan(220)
     * @ORM\Column(name="taille", type="float", precision=10, scale=0, nullable=false)
     * @Assert\NotBlank(message="taille is required")
     */
    private $taille;

    /**
     * @var float
     * @Assert\GreaterThan(0)
     * @Assert\LessThan(7)
     * @ORM\Column(name="heure_activite", type="float", precision=10, scale=0, nullable=false)
     * @Assert\NotBlank(message="heure d'activite is required")
     */
    private $heureActivite;

    /**
     * @var float
     * @Assert\GreaterThan(0)
     * @Assert\LessThan(6)
     * @ORM\Column(name="conso_eau", type="float", precision=10, scale=0, nullable=false)
     * @Assert\NotBlank(message="consomation d'eau is required")
     */
    private $consoEau;

    /**
     * @var int
     *
     * @ORM\Column(name="idUtilisateur", type="integer", nullable=false)
     */
    private $idutilisateur;

    private $captcha;

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
     * @return \DateTime
     */
    public function getDate(): \DateTime
    {
        return $this->date;
    }

    /**
     * @param \DateTime $date
     */
    public function setDate(\DateTime $date): void
    {
        $this->date = $date;
    }

    /**
     * @return float
     */
    public function getPoids()
    {
        return $this->poids;
    }

    /**
     * @param float $poids
     */
    public function setPoids(float $poids)
    {
        $this->poids = $poids;
    }

    /**
     * @return float
     */
    public function getTaille()
    {
        return $this->taille;
    }

    /**
     * @param float $taille
     */
    public function setTaille(float $taille)
    {
        $this->taille = $taille;
    }

    /**
     * @return float
     */
    public function getHeureActivite()
    {
        return $this->heureActivite;
    }

    /**
     * @param float $heureActivite
     */
    public function setHeureActivite(float $heureActivite)
    {
        $this->heureActivite = $heureActivite;
    }

    /**
     * @return float
     */
    public function getConsoEau()
    {
        return $this->consoEau;
    }

    /**
     * @param float $consoEau
     */
    public function setConsoEau(float $consoEau)
    {
        $this->consoEau = $consoEau;
    }

    /**
     * @return int
     */
    public function getIdutilisateur()
    {
        return $this->idutilisateur;
    }

    /**
     * @param int $idutilisateur
     */
    public function setIdutilisateur(int $idutilisateur)
    {
        $this->idutilisateur = $idutilisateur;
    }

    /**
     * @return mixed
     */
    public function getCaptcha()
    {
        return $this->captcha;
    }

    /**
     * @param mixed $captcha
     */
    public function setCaptcha($captcha): void
    {
        $this->captcha = $captcha;
    }


}
