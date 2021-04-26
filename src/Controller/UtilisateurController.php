<?php

namespace App\Controller;

use Dompdf\Dompdf;
use Dompdf\Options;
use App\Form\LoginType;
use App\Form\ModifType;
use App\Entity\Utilisateur;
use App\Form\UtilisateurType;
use App\Form\RecuperermotdepasseType;
use App\Repository\UtilisateurRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;

use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Form\Extension\HttpFoundation\HttpFoundationRequestHandler;


class UtilisateurController extends AbstractController

{

    /**
     * @Route("/admin", name="index")
     */
    public function index(SessionInterface $session): Response
    {
        return $this->render('utilisateur/index.html.twig', [
            'controller_name' => 'UtilisateurController',
            'session'=>$session,
            
        ]);
    }
    /**
     * @Route("/utilisateur", name="indexfront")
     */
    public function indexfront(SessionInterface $session): Response
    {
        return $this->render('utilisateur/indexfront.html.twig', [
            'controller_name' => 'UtilisateurController',
            'session'=>$session,
            
        ]);
    }
    /**
     * @Route("/email", name="email")
     */
    public function email(): Response
    {
        return $this->render('Emails/confirmer.html.twig', [
            'controller_name' => 'UtilisateurController',
            
            
        ]);
    }

    /**
     * @Route("/UtilisateurList", name="UtilisateurList")
     */
    public function UtilisateurList(Request $request,PaginatorInterface $paginator,SessionInterface $session)  
    {
        $repository=$this->getDoctrine()->getRepository(Utilisateur::Class);
        $Utilisateurs=$repository->findAll();
        $Utilisateurs = $paginator->paginate(
            $Utilisateurs,
            $request->query->getInt('page',1),
           
        );
        $male=$this->getDoctrine()->getRepository(Utilisateur::Class)->findMale();
        $female=$this->getDoctrine()->getRepository(Utilisateur::Class)->findFemale();

        return $this->render('Utilisateur/List.html.twig', [
            
            'Utilisateurs' => $Utilisateurs,
            "session"=>$session,
            'Homme'=>$male,
            'Femme'=>$female,
        ]);
    }

 
    /**
     * @Route("/deleteUser/{id}", name="deleteUser")
     */
    public function deleteUser($id)
    {
      
        $em=$this->getDoctrine()->getManager();
        $Utilisateur = $em->getRepository(Utilisateur::class)->find($id);
        $em->Remove($Utilisateur);
         $em->flush();

           return $this->redirectToRoute('UtilisateurList');

    }
    /**
     * @Route("/TrierParDate", name="TrierParDate")
     */
    public function TrierParDate(Request $request,SessionInterface $session, PaginatorInterface $paginator): Response
    {
        $repository = $this->getDoctrine()->getRepository(Utilisateur::class);
        $Utilisateurs = $repository->findByDate();
        $Utilisateurs = $paginator->paginate(
            $Utilisateurs,
            $request->query->getInt('page',1),4
        );
       
        return $this->render('utilisateur/List.html.twig', [
            'Utilisateurs' => $Utilisateurs,
            "session"=>$session,
        ]);
}
/**
     * @Route("/TrierParDate2", name="TrierParDate2")
     */
    public function TrierParDate2(Request $request,SessionInterface $session, PaginatorInterface $paginator): Response
    {
        $repository = $this->getDoctrine()->getRepository(Utilisateur::class);
        $Utilisateurs = $repository->findByDate2();
        $Utilisateurs = $paginator->paginate(
            $Utilisateurs,
            $request->query->getInt('page',1),4
        );
       
        return $this->render('utilisateur/List.html.twig', [
            'Utilisateurs' => $Utilisateurs,
            "session"=>$session,
        ]);
}
/**
     * @Route("/recherche", name="recherche")

     */
    function Recherche(UtilisateurRepository $repository,SessionInterface $session,Request $request){
        $data=$request->get('search');
        $Utilisateurs=$repository->findBy(['nom'=>$data]);

        return $this->render('utilisateur/List.html.twig', [
            'Utilisateurs' => $Utilisateurs,
            "session"=>$session,
        ]);
    }
     /**
     * @Route("/Userbygenre", name="Statistiques")
     */
    public function Statgenre(SessionInterface $session)  
    {
        $male=$this->getDoctrine()->getRepository(Utilisateur::Class)->findMale();
        $female=$this->getDoctrine()->getRepository(Utilisateur::Class)->findFemale();

        return $this->render('Utilisateur/stat.html.twig', [
            'Homme'=>$male,
            'Femme'=>$female,
            "session"=>$session,
        ]);
    }

    /**
     * @Route("/Userbytype", name="Statistiquestype")
     */
    public function Stattype(SessionInterface $session)  
    {
        $coach=$this->getDoctrine()->getRepository(Utilisateur::Class)->findCoach();
        $client=$this->getDoctrine()->getRepository(Utilisateur::Class)->findClient();

        return $this->render('Utilisateur/stat.html.twig', [
            'Coach'=>$coach,
            'Client'=>$client,
            "session"=>$session,
        ]);
    }
        
     /**
     * @Route("/logout", name="logout")
     */
    public function logout(SessionInterface $session){
        $session->clear();
        
        return $this->redirectToRoute('security_log', [
          'session'=>$session,
        ]);

    }

    /**
     * @Route("/pdf", name="pdf", methods={"GET"})
     */
    public function pdf(UtilisateurRepository $UtilisateurRepository,SessionInterface $session): Response
    {
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('utilisateur/pdfusers.html.twig', [
            'Utilisateurs' => $UtilisateurRepository->findAll(),
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (inline view)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => true
        ]);
    }


  

    /**
     * @Route("/Usershow/", name="Usershow")
     */

    public function Usershow(SessionInterface $session)
    {
        if($session->has('utilisateur')){
        $repository=$this->getDoctrine()->getRepository(Utilisateur::Class);
        $Utilisateur=$repository->findBy(array('utilisateur'=>$session->get('utilisateur'))
    );
        return $this->render('utilisateur/showUser.html.twig', [
            'session' => $session,
            'Utilisateur' => $Utilisateur,
        ]);
}

else{
    return $this->redirectToRoute('login');
}
}


}
