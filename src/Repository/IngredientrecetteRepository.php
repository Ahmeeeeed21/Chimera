<?php

namespace App\Repository;

use App\Entity\Ingredientrecette;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
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
            ->Where('ir.idingredient  = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getResult()
            ;
    }

    public function triRecetteComplete()
    {
        return $this->createQueryBuilder('ir')
            ->innerjoin('ir.idrecette','r')
            ->innerjoin('ir.idingredient','i')
            ->addSelect('r')
            ->addSelect('i')
            ->addSelect('ir')
            ->orderBy('ir.idrecette','ASC')
            ->getQuery()
            ->getResult()
            ;
    }

    /*
    public function findOneBySomeField($value): ?Ingredientrecette
    {
        return $this->createQueryBuilder('i')
            ->andWhere('i.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */

    public function AfficherRecetteComplete($idr)
    {
        return $this->createQueryBuilder('r')
            ->join('r.id','c')
            ->addSelect('c')
            ->where('c.id=:idr')
            ->setParameters('idr',$idr)
            ->getQuery()
            ->getResult();
    }
}
