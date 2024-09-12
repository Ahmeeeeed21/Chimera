<?php

namespace App\Controller;

use App\Entity\Commentaire;
use App\Entity\PublicationPhysique;
use App\Form\PublicationPhysiqueType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
require 'C:\wamp64\www\SlowLifeWeb\vendor\autoload.php';
use Twilio\Rest\Client;
use Endroid\QrCodeBundle\Response\QrCodeResponse;
/**
 * @Route("/publication/physique")
 */
class PublicationPhysiqueController extends AbstractController
{
    /**
     * @Route("/physique/search", name="recherche", methods={"GET"})
     */

    public function searchAction(Request $request)
    {
        $pieChart = $this->stats();
        $data = $request->get('search');


        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery('SELECT e FROM App\Entity\PublicationPhysique
         e WHERE (e.nom   LIKE :data) OR (e.description LIKE :data)')
            ->setParameter('data', '%'.$data.'%');


        $events = $query->getResult();

        return $this->render('publication_physique/index.html.twig', [
            'publication_physiques' => $events,
            'piechart' => $pieChart,
        ]);
    }
    /**
     * @Route("/tri", name="tri")
     */

    public function tri(Request $request)
    { $pieChart = $this->stats();
        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery(
            'SELECT e FROM App\Entity\PublicationPhysique
         e ORDER BY e.duree ');


        $candidats = $query->getResult();

        return $this->render('publication_physique/index.html.twig', array(
            'publication_physiques' => $candidats,
         'piechart' => $pieChart,
        ));

    }
    /**
     * @Route("/tri", name="trii")
     */

    public function trir(Request $request)
    { $pieChart = $this->stats();
        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery(
            'SELECT e FROM App\Entity\PublicationPhysique
         e ORDER BY e.repetition ');


        $candidats = $query->getResult();

        return $this->render('publication_physique/index.html.twig', array(
            'publication_physiques' => $candidats,
            'piechart' => $pieChart,
            ));

    }
    /**
     * @Route("/physique/typee", name="recherchetype", methods={"GET"})
     */

    public function searchBytype(Request $request)
    {
        $pieChart = $this->stats();
        $data = $request->get('typee');


        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery('SELECT e FROM App\Entity\PublicationPhysique
         e WHERE e.type   LIKE :data')
            ->setParameter('data', '%'.$data.'%');


        $events = $query->getResult();

        return $this->render('publication_physique/index.html.twig', [
            'publication_physiques' => $events,
            'piechart' => $pieChart,
        ]);
    }
    /**
     * @Route("/physique/typeefront", name="recherchetypef", methods={"GET"})
     */

    public function searchBytypef(Request $request)
    {
        $pieChart = $this->stats();
        $data = $request->get('typee');


        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery('SELECT e FROM App\Entity\PublicationPhysique
         e WHERE e.type   LIKE :data')
            ->setParameter('data', '%'.$data.'%');


        $events = $query->getResult();

        return $this->render('publication_physique/showfront.html.twig', [
            'publication_physiques' => $events,
            'piechart' => $pieChart,
        ]);
    }

    /**
     * @Route("/", name="publication_physique_index", methods={"GET"})
     */
    public function index(): Response
    {
        $pieChart = $this->stats();
        $publicationPhysiques = $this->getDoctrine()
            ->getRepository(PublicationPhysique::class)
            ->findAll();

        return $this->render('publication_physique/index.html.twig', [
            'publication_physiques' => $publicationPhysiques,
            'piechart' => $pieChart,
        ]);
    }

    public function stats ()
    {
        $musculation = $this->getDoctrine()
            ->getRepository(PublicationPhysique::class)
            ->findBy(['type'=>'musculation']);

        $equilibre = $this->getDoctrine()
            ->getRepository(PublicationPhysique::class)
            ->findBy(['type'=>'equilibre']);

        $assouplisement = $this->getDoctrine()
            ->getRepository(PublicationPhysique::class)
            ->findBy(['type'=>'assouplisement']);
        $cardio = $this->getDoctrine()
            ->getRepository(PublicationPhysique::class)
            ->findBy(['type'=>'cardio']);

        $pieChart = new PieChart();
        $pieChart->getData()->setArrayToDataTable(
            [['publication', 'type'],
                ['musculation',     count($musculation)],
                ['equilibre',    count($equilibre)],
                ['assouplisement',  count($assouplisement)],
                ['cardio',  count($cardio)],
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
     * @Route("/physique/showfront", name="publication_physique_showfront", methods={"GET"})
     */
    public function showfront(): Response
    {
        $publicationPhysiques = $this->getDoctrine()
            ->getRepository(PublicationPhysique::class)
            ->findAll();

        return $this->render('publication_physique/showfront.html.twig', [
            'publication_physiques' => $publicationPhysiques,
        ]);
    }
    /**
     * @Route("/new", name="publication_physique_new", methods={"GET","POST"})
     */
    public function new(Request $request,\Swift_Mailer $mailer): Response
    {
        $publicationPhysique = new PublicationPhysique();
        $form = $this->createForm(PublicationPhysiqueType::class, $publicationPhysique);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($publicationPhysique);
            $entityManager->flush();
// Your Account SID and Auth Token from twilio.com/console
           // $sid = 'ACb91ce0cf1eb96216604bbac62fa63c7c';
            //$token = 'db04aa36dd4dab9238596bb9ac62ef82';
            //$client = new Client($sid, $token);

// Use the client to do fun stuff like send text messages!
            //$client->messages->create(
            // the number you'd like to send the message to
              //  '+21629031784',
                //[
                    // A Twilio phone number you purchased at twilio.com/console
                  //  'from' => '+17036345247',
                    // the body of the text message you'd like to send
                    //'body' => 'Hey Jenny! Good luck on the bar exam!'
                //]
            //);
            $message = (new \Swift_Message('Hello Email'))
                ->setFrom('wassim.benammar@esprit.tn')
                ->setTo('benammarwassim6@gmail.com')
                ->setBody(
                    $this->renderView(
                    // templates/emails/registration.html.twig
                        'emails/registration.html.twig'

                    ),
                    'text/html'
                )

            ;

            $mailer->send($message);

            return $this->redirectToRoute('publication_physique_index');
        }

        return $this->render('publication_physique/new.html.twig', [
            'publication_physique' => $publicationPhysique,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="publication_physique_show", methods={"GET"})
     */
    public function show(PublicationPhysique $publicationPhysique,$id): Response
    {$Commentaire = $this->getDoctrine()
        ->getRepository(Commentaire::class)
        ->findBy(array('idPublication'=>$id));
        return $this->render('publication_physique/show.html.twig', [

            'publication_physique' => $publicationPhysique,
            'com' => $Commentaire,
            'id' => $id,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="publication_physique_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, PublicationPhysique $publicationPhysique): Response
    {
        $form = $this->createForm(PublicationPhysiqueType::class, $publicationPhysique);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('publication_physique_index');
        }

        return $this->render('publication_physique/edit.html.twig', [
            'publication_physique' => $publicationPhysique,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="publication_physique_delete", methods={"POST"})
     */
    public function delete(Request $request, PublicationPhysique $publicationPhysique): Response
    {
        if ($this->isCsrfTokenValid('delete'.$publicationPhysique->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($publicationPhysique);
            $entityManager->flush();
        }

        return $this->redirectToRoute('publication_physique_index');
    }
    /**
     * @Route("/", name="register", methods={"GET"})
     */



}
