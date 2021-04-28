<?php

namespace App\Repository;

use App\Entity\Ingredientrecette;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\Query\Expr\OrderBy;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Ingredientrecette|null find($id, $lockMode = null, $lockVersion = null)
 * @method Ingredientrecette|null findOneBy(array $criteria, array $orderBy = null)
 * @method Ingredientrecette[]    findAll()
 * @method Ingredientrecette[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class IngredientrecetteRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Ingredientrecette::class);
    }

    // /**
    //  * @return Ingredientrecette[] Returns an array of Ingredientrecette objects
    //  */

    public function findRecetteComplete($value)
    {
        return $this->createQueryBuilder('ir')
            ->innerjoin('ir.idrecette','r')
            ->innerjoin('ir.idingredient','i')
            ->addSelect('r')
            ->addSelect('i')
            ->addSelect('ir')
            ->Where('ir.idrecette  = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getResult()
        ;
    }

    public function findbyIngredient($value)
    {
        return $this->createQueryBuilder('ir')
            ->innerjoin('ir.idrecette','r')
            ->innerjoin('ir.idingredient','i')
            ->addSelect('r')
            ->addSelect('i')
            ->addSelect('ir')
            ->Where('i.nom = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getResult()
            ;
    }

    public function search($name)
    {
        return $this->createQueryBuilder('ir')
            ->innerjoin('ir.idrecette','r')
            ->innerjoin('ir.idingredient','i')
            ->addSelect('i')
            ->addSelect('r')
            ->addSelect('ir')
            ->where('ir.idrecette.nomrecette LIKE :val')
            ->setParameter('val', $name.'%')
            ->getQuery()
            ->getResult();
    }
    public function triRecetteComplete()
    {
        return $this->createQueryBuilder('ir')
            ->innerjoin('ir.idrecette','r')
            ->innerjoin('ir.idingredient','i')
            ->addSelect('r')
            ->addSelect('i')
            ->addSelect('ir')
            ->getQuery()
            ->getResult()
            ;
    }
    public function triParCritere($critere)
    {
        return $this->createQueryBuilder('ir')
            ->innerjoin('ir.idrecette','r')
            ->innerjoin('ir.idingredient','i')
            ->addSelect('r')
            ->addSelect('i')
            ->addSelect('ir')
            ->orderBy($critere,'ASC')
            ->getQuery()
            ->getResult();
    }
    public function triPar2Critere()
    {
        return $this->createQueryBuilder('ir')
            ->innerjoin('ir.idrecette','r')
            ->innerjoin('ir.idingredient','i')
            ->addSelect('ir')
            ->addOrderBy('r.nomrecette', 'ASC')
            ->addOrderBy('i.nom', 'ASC')
            ->getQuery()
            ->getResult();
    }


}
