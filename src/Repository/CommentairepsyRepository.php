<?php

namespace App\Repository;

use App\Entity\Comment;
use App\Entity\Commentaire;
use App\Entity\Commentairepsy;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Commentairepsy|null find($id, $lockMode = null, $lockVersion = null)
 * @method Commentairepsy|null findOneBy(array $criteria, array $orderBy = null)
 * @method Commentairepsy[]    findAll()
 * @method Commentairepsy[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class CommentairepsyRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Commentairepsy::class);
    }

    // /**
    //  * @return Comment[] Returns an array of Comment objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('c.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Comment
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
