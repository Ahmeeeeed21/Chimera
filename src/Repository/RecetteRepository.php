<?php

namespace App\Repository;

use App\Entity\Recette;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;
use mysql_xdevapi\Exception;
use Symfony\Component\HttpKernel\Exception\NotFoundHttpException;

/**
 * @method Recette|null find($id, $lockMode = null, $lockVersion = null)
 * @method Recette|null findOneBy(array $criteria, array $orderBy = null)
 * @method Recette[]    findAll()
 * @method Recette[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class RecetteRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Recette::class);
    }

    // /**
    //  * @return Recette[] Returns an array of Recette objects
    //  */

    public function findByTypeEtat($type,$etat,$id)
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.typerecette = :val')
            ->andWhere('r.etat = :eta')
            ->andWhere('r.idrecette != :id')
            ->setParameter('val', $type)
            ->setParameter('eta', $etat)
            ->setParameter('id', $id)
            ->setMaxResults(3)
            ->getQuery()
            ->getResult();
    }
    public function search($name,$type,$id)
    {
        return $this->createQueryBuilder('r')
            ->where('r.nomrecette LIKE :val')
            ->setParameter('val', $name.'%')
            ->Andwhere('r.typerecette LIKE :type')
            ->setParameter('type', $type.'%')
            ->Andwhere('r.idcoach = :id')
            ->setParameter('id', $id)
            ->getQuery()
            ->getResult();
    }


    public function findRecettebyId($value): ?Recette
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.idrecette = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult();
    }

    public function findRecettebyEtat($value)
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.etat = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getResult();
    }
    public function findRecettebyType($value)
    {
        return $this->createQueryBuilder('r')

            ->andWhere('r.typerecette = :val')
            ->setParameter('val', $value)
            ->getQuery()->getResult();
    }
    public function findRecettebyType2($value){
$dqlresult=$this->getEntityManager()->createQuery("select r from App:Recette r where r.typerecette='$value' ");
 return($dqlresult->getResult());

    }
    public function AccepterRefuserRecette($etat,$idr)
    {
        $query = $this->getEntityManager()->createQuery('update App:Recette r set r.etat = :eta where r.idrecette = :idr')
            ->setParameters([
                'eta' => $etat,
                'idr' => $idr
            ]);
        return $query->getResult();
    }





}
