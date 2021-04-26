<?php

namespace App\Controller;

use App\Entity\Ingredient;
use App\Entity\Recette;
use App\Entity\Ingredientrecette;
use App\Form\IngredientrecetteType;
use App\Form\RecetteType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\RecetteRepository;
use App\Repository\IngredientrecetteRepository;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use Knp\Snappy\Pdf;
use Knp\Bundle\SnappyBundle\Snappy\Response\PdfResponse;
require 'C:\Users\Yassine\Desktop\webweb\pidev\vendor\autoload.php';
use Twilio\Rest\Client;
/**
 * @Route("/recette")
 */
class RecetteController extends AbstractController
{
    /**
     * @Route("/", name="recette_index", methods={"GET"})
     */
    public function index(): Response
    {
        $recettes = $this->getDoctrine()
            ->getRepository(Recette::class)
            ->findAll();
        return $this->render('recette/index.html.twig', [
            'recettes' => $recettes,

        ]);
    }
    /**
     * @Route("/front", name="recette_front_index", methods={"GET"})
     */
    public function index_front(): Response
    {
        $recettes = $this->getDoctrine()
            ->getRepository(Recette::class)
            ->findBy(array('etat'=>'Accepter'));

        return $this->render('recette/index_front.html.twig', [
            'recettes' => $recettes,
        ]);
    }
    /**
     * @Route("/back", name="recette_back_index", methods={"GET"})
     */
    public function index_back(RecetteRepository $rec): Response
    {
        $recettes1 = $this->getDoctrine()
            ->getRepository(Recette::class)
            ->findBy(array('etat'=>'En Attente'));

        $recettes2 = $this->getDoctrine()
            ->getRepository(Recette::class)
            ->findBy(array('etat'=>'Accepter'));

        $recettes3 = $this->getDoctrine()
            ->getRepository(Recette::class)
            ->findBy(array('etat'=>'Refuser'));
        $int = $rec->countByEtat('Accepter');
        $pieChart = new PieChart();
        $pieChart->getData()->setArrayToDataTable(
            [['Etat', 'Nombre'],
                ['En Attente',    count($recettes1)],
                ['Accepter',     count($recettes2)],
                ['Refuser',  count($recettes3)],

            ]
        );
        $pieChart->getOptions()->setTitle('Les etat de recettes');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(900);
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#009900');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(20);


        return $this->render('recette/confirmation.html.twig', [
            'recettew' => $recettes1,
            'recettea' => $recettes2,
            'recetter' => $recettes3,
            'piechart' => $pieChart,
             'count' => $rec->countByEtat('Accepter')
        ]);
    }
    /**
     * @Route("/{typerecette}", name="recette_afficher", methods={"GET"})
     */
    public function afficher(RecetteRepository $rec,Recette $recette)
    {
        dump($recette);
        $recettes = $rec->findRecettebyType($recette->getTyperecette());
        if (!$recettes) {
            return $this->render('recette/index.html.twig', ['recettes' => []]);
        } else {
            return $this->render('recette/index.html.twig', [
                'recettes' => $recettes,
            ]);

        }
    }
    /**
     * @Route("/front/{typerecette}", name="recette_front_afficher", methods={"GET"})
     */
    public function afficher_front(RecetteRepository $rec,Recette $recette)
    {
        dump($recette);
        $recettes = $this->getDoctrine()
            ->getRepository(Recette::class)
            ->findBy(array('etat'=>'Accepter','typerecette'=>$recette->getTyperecette()));

            return $this->render('recette/index_front.html.twig', [
                'recettes' => $recettes,
            ]);

    }

    /**
     * @Route("/new", name="recette_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $recette = new Recette();
        $recette->setEtat("En Attente");
        $form = $this->createForm(RecetteType::class, $recette);
        $form->handleRequest($request);


        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($recette);
            $entityManager->flush();

            return $this->redirectToRoute('recette_index');
        }

        return $this->render('recette/new.html.twig', [
            'recette' => $recette,
            'form' => $form->createView(),

        ]);
    }

    /**
     * @Route("/{idrecette}", name="recette_show", methods={"GET"})
     */
    public function show(Recette $recette): Response
    {
        return $this->render('recette/show.html.twig', [
            'recette' => $recette,
        ]);
    }

    /**
     * @Route("/{idrecette}/edit", name="recette_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Recette $recette): Response
    {

        $recette->setEtat("En Attente");
        $form = $this->createForm(RecetteType::class, $recette);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('recette_index');
        }

        return $this->render('recette/edit.html.twig', [
            'recette' => $recette,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idrecette}", name="recette_delete", methods={"POST"})
     */
    public function delete(Request $request, Recette $recette): Response
    {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($recette);
            $entityManager->flush();
    return $this->redirectToRoute('recette_index');
        $sid = 'AC2e3f103543a521c05ddbd7f53f95f5bd';
        $token = '60024a71d98cc514cca9d408c74bf1e2';
        $client = new Client($sid, $token);

// Use the client to do fun stuff like send text messages!
        $client->messages->create(
        // the number you'd like to send the message to
            '+21622525030',
            [
                // A Twilio phone number you purchased at twilio.com/console
                'from' => '+16157518411',
                // the body of the text message you'd like to send
                'body' => 'Votre recette a été supprimer'
            ]
        );

    }
    /**
     * @Route("/{idrecette}/accepter", name="Accepter", methods={"GET"})
     */
    public function Accepter(Recette $recette,RecetteRepository $rep,\Swift_Mailer $mailer): Response
    {
       $val = $rep->AccepterRefuserRecette("Accepter",$recette->getIdrecette());
        $message = (new \Swift_Message('SlowLife'))
            ->setFrom('slowlife.testpi@gmail.com', 'SlowLife')
            ->setTo('yassine.benamor@esprit.tn')
            ->setSubject('')
            ->setBody("Hello");
        $mailer->send($message) ;
        return $this->redirectToRoute('recette_back_index');
    }
    /**
     * @Route("/{idrecette}/Refuser", name="Refuser", methods={"GET"})
     */
    public function Rejeter(Recette $recette,RecetteRepository $rep,\Swift_Mailer $mailer): Response
    {
        $val = $rep->AccepterRefuserRecette("Refuser",$recette->getIdrecette());
        $message = (new \Swift_Message('SlowLife'))
            ->setFrom('slowlife.testpi@gmail.com', 'SlowLife')
            ->setTo('yassine.benamor@esprit.tn')
            ->setSubject('')
            ->setBody("Bonjour cher coach, votre recette".$recette->getNomrecette()."a été refuser");
        $mailer->send($message) ;
        return $this->redirectToRoute('recette_back_index');
    }
    /**
     * @Route("/{idrecette}/show_more", name="show_more", methods={"GET"})
     */
    public function AfficherRecette(Recette $recette,RecetteRepository $rec,IngredientRecetteRepository $rep): Response
    {
        $RecetteComplete = $rep->findRecetteComplete($recette->getIdrecette());
        $recette = $rec->findRecettebyId($recette);
        $Related = $rec->findByTypeEtat($recette->getTyperecette(),'Accepter',$recette->getIdrecette());
        $Calorie=0;
        return $this->render('recette/ShowMore.html.twig', [
            'recetteComplete' => $RecetteComplete,
            'recette' => $recette,
            'Relateds' => $Related,
            'Calorie' => $Calorie
        ]);
    }

    /**
     * @Route("/{idrecette}/show_more/pdf", name="pdf", methods={"GET"})
     */
    public function PDF(Recette $recette,RecetteRepository $rec,IngredientRecetteRepository $rep,Pdf $snappy)
    {
        $RecetteComplete = $rep->findRecetteComplete($recette->getIdrecette());
        $recette = $rec->findRecettebyId($recette);
        $Related = $rec->findByTypeEtat($recette->getTyperecette(),'Accepter',$recette->getIdrecette());
        $Calorie=0;
        $html = $this->renderView('recette/GeneratePdf.html.twig',[
            'recetteComplete' => $RecetteComplete,
            'recette' => $recette,
            'Relateds' => $Related,
            'Calorie' => $Calorie
        ]);

        $filename = 'myFirstSnappyPDF';

        return new Response(
            $snappy->getOutputFromHtml($html),
            200,
            array(
                'Content-Type'          => 'application/pdf',
                'Content-Disposition'   => 'inline; filename="'.$filename.'.pdf"'
            )
        );
    }
    /**
     * @Route("/r/recherche", name="recherche", methods={"GET"})
     */
    public function RechercheRecette(Request $request,RecetteRepository $rec): Response
    {
        $nom = $request->get('search');
        $search = $rec->search($nom);
        return $this->render('recette/index.html.twig', [
            'recettes' => $search,
        ]);
       // return new Response(json_encode($search));
    }

    /**
     * @Route("/searchRecette ", name="searchRecette")
     */
    public function searchRecette(Request $request,NormalizerInterface $Normalizer,RecetteRepository $repository)
{
    $requestString=$request->get('searchValue');
    $students = $repository->search($requestString);
    $jsonContent = $Normalizer->normalize($students, 'json',['groups'=>'recettes:read']);
    $retour=json_encode($jsonContent);
    return new Response($retour);
}


}
