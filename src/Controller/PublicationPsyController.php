<?php

namespace App\Controller;

use App\Entity\Commentairepsy;
use App\Entity\PublicationPsy;
use App\Form\PublicationPsyType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\CommentairepsyRepository;
use App\Repository\PublicationPsyRepository;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use MercurySeries\FlashyBundle\FlashyNotifier;


/**
 * @Route("/publication/psy")
 */
class PublicationPsyController extends AbstractController
{
    /**
     * @Route("/search", name="recherche2", methods={"GET"})
     */

    public function searchAction(Request $request)
    {
        $pieChart = $this->stats();
        $data = $request->get('search');


        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery('SELECT e FROM App\Entity\PublicationPsy
         e WHERE (e.sujet   LIKE :data) OR (e.description LIKE :data)')
            ->setParameter('data', '%'.$data.'%');


        $events = $query->getResult();

        return $this->render('publication_psy/index.html.twig', [
            'publication_psies' => $events,
            'piechart' => $pieChart,
        ]);
    }
    /**
     * @Route("/tri", name="tri2")
     */

    public function tri(Request $request)
    { $pieChart = $this->stats();
        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery(
            'SELECT e FROM App\Entity\PublicationPsy
         e ORDER BY e.sujet ASC ');


        $candidats = $query->getResult();

        return $this->render('publication_psy/index.html.twig', array(
            'publication_psies' => $candidats,
            'piechart' => $pieChart,
            ));
    }
    /**
     * @Route("/typee/psy/f", name="recherchetype2", methods={"GET"})
     */

    public function searchBytypef(Request $request)
    {
        $pieChart = $this->stats();
        $data = $request->get('typee');


        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery('SELECT e FROM App\Entity\PublicationPsy
         e WHERE e.type   LIKE :data')
            ->setParameter('data', '%'.$data.'%');


        $events = $query->getResult();

        return $this->render('publication_psy/showfront.html.twig', [
            'publication_psies' => $events,
            'piechart' => $pieChart,
        ]);
    }
    /**
     * @Route("/typee/psy", name="recherchetype2f", methods={"GET"})
     */

    public function searchBytype(Request $request)
    {
        $pieChart = $this->stats();
        $data = $request->get('typee');


        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery('SELECT e FROM App\Entity\PublicationPsy
         e WHERE e.type   LIKE :data')
            ->setParameter('data', '%'.$data.'%');


        $events = $query->getResult();

        return $this->render('publication_psy/index.html.twig', [
            'publication_psies' => $events,
            'piechart' => $pieChart,
        ]);
    }
    /**
     * @Route("/psy/id", name="publication_psy_index", methods={"GET"})
     */
    public function index(): Response
    {        $pieChart = $this->stats();
        $publicationPsies = $this->getDoctrine()
            ->getRepository(PublicationPsy::class)
            ->findAll();

        return $this->render('publication_psy/index.html.twig', [
            'publication_psies' => $publicationPsies,
            'piechart' => $pieChart,
        ]);
    }
    public function stats ()
    {
        $depression = $this->getDoctrine()
            ->getRepository(PublicationPsy::class)
            ->findBy(['type'=>'depression']);

        $socialisation = $this->getDoctrine()
            ->getRepository(PublicationPsy::class)
            ->findBy(['type'=>'socialisation']);

        $integrationpro = $this->getDoctrine()
            ->getRepository(PublicationPsy::class)
            ->findBy(['type'=>'integration pro']);


        $pieChart = new PieChart();
        $pieChart->getData()->setArrayToDataTable(
            [['publication', 'type'],
                ['depression',     count($depression)],
                ['socialisation',    count($socialisation)],
                ['integration pro',  count($integrationpro)],

            ]
        );
        $pieChart->getOptions()->setTitle('Les Publications par type');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(900);
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#009900');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(20);
        return $pieChart;
    }
    /**
     * @Route("/psy/showfront", name="publication_psy_showfront", methods={"GET"})
     */
    public function showfront(): Response
    {
        $publicationPsies = $this->getDoctrine()
            ->getRepository(PublicationPsy::class)
            ->findAll();

        return $this->render('publication_psy/showfront.html.twig', [
            'publication_psies' => $publicationPsies,
        ]);
    }
    /**
     * @Route("/new", name="publication_psy_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $publicationPsy = new PublicationPsy();
        $form = $this->createForm(PublicationPsyType::class, $publicationPsy);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($publicationPsy);
            $entityManager->flush();

            return $this->redirectToRoute('publication_psy_index');
        }

        return $this->render('publication_psy/new.html.twig', [
            'publication_psy' => $publicationPsy,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}/show", name="publication_psy_show", methods={"GET"})
     */
    public function show(PublicationPsy $publicationPsy,$id): Response
    {$Commentairepsy = $this->getDoctrine()
        ->getRepository(Commentairepsy::class)
        ->findBy(array('idPublication2'=>$id));

        return $this->render('publication_psy/show.html.twig', [

            'publication_psy' => $publicationPsy,
            'com' => $Commentairepsy,
            'id' => $id,
        ]);
    }

//    /**
 /*    * @Route("/{id}", name="comment", methods={"GET"})
     * @param $id
     * @param PublicationPsyRepository $repo
     * @param $Publicationpsy
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     */
  /*  public function comment($id,PublicationPsyRepository $repo, Request $request ): Response
    { $em = $this->getDoctrine()->getManager();
        $publicationPsy = $repo->find($id);
$em->flush();
$comment = new Commentairepsy;
        $commentForm = $this->createForm(CommentairepsyType::class, $comment);

        $commentForm->handleRequest($request);
         if($commentForm->isSubmitted() && $commentForm->isValid()){
             $comment->setPublicationpsy($publicationPsy);
             $parentid = $commentForm->get("parent_id")->getData();
             $em = $this->getDoctrine()->getManager();
             if($parentid != null){
                 $parent = $em->getRepository(Commentairepsy::class)->find($parentid);
             }
             $comment->setParent($parent ?? null);
             $em->persist($comment);
             $em->flush();

             return $this->redirectToRoute('show', ['id' => $publicationPsy->getId() ]);

    }
              return $this->render('publication_psy/show.html.twig', [
                  'publication_psy' => $publicationPsy,
                  'compsy' => $em, 'commentForm' => $commentForm->createView()]);
    }
*/
    /**
     * @Route("/{id}/edit", name="publication_psy_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, PublicationPsy $publicationPsy): Response
    {
        $form = $this->createForm(PublicationPsyType::class, $publicationPsy);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('publication_psy_index');
        }

        return $this->render('publication_psy/edit.html.twig', [
            'publication_psy' => $publicationPsy,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="publication_psy_delete", methods={"POST"})
     */
    public function delete(Request $request, PublicationPsy $publicationPsy): Response
    {
        if ($this->isCsrfTokenValid('delete'.$publicationPsy->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($publicationPsy);
            $entityManager->flush();
        }

        return $this->redirectToRoute('publication_psy_index');
    }
}
